/*
 * GNU General Public License v3 (GPL-3)
 *
 * Copyright 2017
 * Christophe Van Waesberghe <contact@chrisv.be>
 * Amélie Courtin <a.courtin@students.ephec.be>
 * Mathieu Rousseau <m.rousseau@students.ephec.be>
 * Nathan Dolinsky <n.dolinski@students.ephec.be>
 * (Groupe 05, 2016/17)
 * 
 * The DTE Java Interface is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * The DTE Java Interface is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with the DTE Java Interface and the whole DTE Project.  If not, see <http://www.gnu.org/licenses/>.
 */
package be.chrisv.dte.view;

import be.chrisv.dte.controller.Controller;
import be.chrisv.dte.model.Model;
import java.util.Observable;
import java.util.Observer;

public abstract class View extends javax.swing.JFrame implements Observer {

    protected Model model;
    protected Controller controller;

    /**
     * Full View constructor
     * @param controller Controller to link with this View
     * @param model Model to link with this View
     */
    public View(Controller controller, Model model) {
        this.model = model;
        this.controller = controller;
    }

    /**
     * Empty View constructor
     */
    public View() {

    }

    /**
     * Add an observable object
     *
     * @param observable Observable object
     */
    public void addObservable(Observable observable) {
        observable.addObserver(this);
    }

    /**
     * Must print the current AlertLevel on the View
     */
    public abstract void printAlertLevel();
}
