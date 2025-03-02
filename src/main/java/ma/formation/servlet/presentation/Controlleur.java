package ma.formation.servlet.presentation;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.formation.servlet.service.IService;
import ma.formation.servlet.service.ServiceImpl;
import ma.formation.servlet.service.model.Personne;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(urlPatterns = "/do/*",
        initParams = {
                @WebInitParam(name = "urlEdit", value = "/view/edit.jsp"),
                @WebInitParam(name = "urlErreurs", value = "/view/erreurs.jsp"),
                @WebInitParam(name = "urlList", value = "/view/list.jsp") })
public class Controlleur extends HttpServlet {
    private String urlErreurs = null;
    private List<String> erreursInitialisation = new ArrayList<String>();
    private String[] parametres = { "urlList", "urlEdit", "urlErreurs" };
    private Map<String, String> params = new HashMap<>();
    IService service = null;

    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String valeur = null;
        for (int i = 0; i < parametres.length; i++) {
            valeur = config.getInitParameter(parametres[i]);
            if (valeur == null) {
                erreursInitialisation.add("Le paramètre [" + parametres[i] + "] n'a pas été initialisé");
            } else {
                params.put(parametres[i], valeur);
            }
        }
        urlErreurs = config.getInitParameter("urlErreurs");
        if (urlErreurs == null)

            throw new ServletException("Le paramètre [urlErreurs] n'a pas été initialisé");
        service = new ServiceImpl();
        service.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (erreursInitialisation.size() != 0) {
            request.setAttribute("erreurs", erreursInitialisation);
            getServletContext().getRequestDispatcher(urlErreurs).forward(request, response);
        }
        String methode = request.getMethod().toLowerCase();
        String action = request.getPathInfo();
        if (action == null) {
            action = "/list";
        }
        if (methode.equals("get") && action.equals("/list")) {
            doListPersonnes(request, response);
            return;
        }
        if (methode.equals("get") && action.equals("/delete")) {
            doDeletePersonne(request, response);
            return;
        }
        if (methode.equals("get") && action.equals("/edit")) {
            doEditPersonne(request, response);
            return;
        }
        if (methode.equals("post") && action.equals("/validate")) {
            doValidatePersonne(request, response);
            return;
        }
        doListPersonnes(request, response);
    }

    private void doListPersonnes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("personnes", service.getAll());
        getServletContext().getRequestDispatcher((String) params.get("urlList")).forward(request, response);
    }

    private void doEditPersonne(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Personne thePersonne = null;
        if (id != -1) {
            thePersonne = service.getById(id);
        } else {
            thePersonne = new Personne();
            thePersonne.setId(-1);
        }
        request.setAttribute("thePersonne", thePersonne);
        getServletContext().getRequestDispatcher((String) params.get("urlEdit")).forward(request, response);
    }

    private void doDeletePersonne(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        service.delete(id);
        response.sendRedirect("list");
    }

    public void doValidatePersonne(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean formulaireErrone = false;
        boolean erreur;
        String prenom = request.getParameter("prenom").trim();
        if (prenom.length() == 0) {
            request.setAttribute("erreurPrenom", "Le prénom est obligatoire");
            formulaireErrone = true;
        }
        String nom = request.getParameter("nom").trim();
        if (nom.length() == 0) {
            request.setAttribute("erreurNom", "Le nom est obligatoire");
            formulaireErrone = true;
        }
        Date dateNaissance = null;
        try {
            dateNaissance = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateNaissance").trim());
        } catch (ParseException e) {
            request.setAttribute("erreurDateNaissance", "Date incorrecte");
            formulaireErrone = true;
        }
        boolean marie = Boolean.parseBoolean(request.getParameter("marie").trim());
        int nbEnfants = 0;
        erreur = false;
        try {
            nbEnfants = Integer.parseInt(request.getParameter("nbEnfants").trim());
            if (nbEnfants < 0) {
                erreur = true;
            }
        } catch (NumberFormatException ex) {
            erreur = true;
        }
        if (erreur) {
            request.setAttribute("erreurNbEnfants", "Nombre d'enfants incorrect");
            formulaireErrone = true;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        if (formulaireErrone) {
            showFormulaire(request, response, "");
            return;
        }
        Personne personne = new Personne(id, prenom, nom, dateNaissance, marie, nbEnfants);
        try {
            service.save(personne);
        } catch (Exception ex) {
            showFormulaire(request, response, ex.getMessage());
            return;
        }
        response.sendRedirect("list");
    }

    private void showFormulaire(HttpServletRequest request, HttpServletResponse response, String erreurEdit)
            throws ServletException, IOException {
        Personne thePersonne = new Personne();
        request.setAttribute("erreurEdit", erreurEdit);
        thePersonne.setId(Integer.parseInt(request.getParameter("id")));
        thePersonne.setPrenom(request.getParameter("prenom").trim());
        thePersonne.setNom(request.getParameter("nom").trim());
        try {
            thePersonne.setDateNaissance(
                    new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateNaissance").trim()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        thePersonne.setMarie(Boolean.getBoolean(request.getParameter("marie")));
        try {
            thePersonne.setNbEnfants(Integer.parseInt(request.getParameter("nbEnfants").trim()));
        } catch (Exception e) {
            thePersonne.setNbEnfants(0);
        }
        request.setAttribute("thePersonne", thePersonne);
        getServletContext().getRequestDispatcher((String) params.get("urlEdit")).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}

