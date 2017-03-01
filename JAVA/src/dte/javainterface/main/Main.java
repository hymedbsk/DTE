package dte.javainterface.main;

import dte.javainterface.controller.Controller;
import dte.javainterface.model.Model;
import dte.javainterface.view.CliView;
import dte.javainterface.view.GuiView;

public class Main {

    private Model model;
    private Controller guiController;
    private Controller cliController;
    private CliView cli;
    private GuiView gui;

    /**
     * Setup the Model, the controller and the view.
     */
    public void Main() {

        model = new Model();
        guiController = new Controller(model);
        cliController = new Controller(model);

        gui = new GuiView(guiController, model);
        cli = new CliView(cliController, model);

        guiController.addView(gui);
        cliController.addView(cli);

        gui.addObservable(model);
        cli.addObservable(model);
        //gui.setVisible(true);

    }

    /**
     * Launch the app.
     *
     * @param args Not used for the moment (Will probably be used to pass some
     * settings)
     */
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(Main::new);

    }

}
