/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mz.osoma.editor.service;

import co.mz.osoma.editor.modelo.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;

import co.mz.osoma.editor.controlador.MainGUIController;

/**
 * @author Lenovo
 */
public class MyTreeCell extends TextFieldTreeCell<Object> {

    private ContextMenu questionMenu, examMenu, choiceMenu;
    private MainGUIController controller;

    public MyTreeCell(MainGUIController mainGUIController) {

        this.controller = mainGUIController;

        MenuItem menu1 = MenuItemBuilder.create().text("Multi Choice (Single Select)").onAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        try {
                            QuestionMultiChoice questionMultiChoice = new QuestionMultiChoice();
                            questionMultiChoice.setQuestionType(QuestionType.SIGLE);
                            TreeItem<Object> node = mainGUIController.makeBranch(questionMultiChoice, mainGUIController.getSeletedItem());

                            for (int i = 0; i<questionMultiChoice.getTotalOfChoices(); i++){
                                mainGUIController.makeBranch(new Choice(), node);
                            }

                        }catch (Exception e){

                        }
                    }
                }
        ).build();
        MenuItem menu2 = MenuItemBuilder.create().text("Multi Choice (Multiple Select)").build();
        MenuItem menu3 = MenuItemBuilder.create().text("Multi Choice (Single Select) With Case of Study").onAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {

                    }
                }
        ).build();
        MenuItem menu4 = MenuItemBuilder.create().text("Multi Choice (Multiple Select) With Case of Study").build();
        examMenu
                = ContextMenuBuilder.create()
                .items(
                        MenuBuilder.create().text("New Question").items(menu1, menu2, menu3, menu4).build(),
                        MenuItemBuilder.create()
                                .text("Delete")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                ).disable(true)
                                .build()
                )
                .build();

        choiceMenu = ContextMenuBuilder.create()
                .items(
                        MenuItemBuilder.create()
                                .text("Move Up")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                ).disable(true)
                                .build(),
                        MenuItemBuilder.create()
                                .text("Move Down")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                ).disable(true)
                                .build(),

                        MenuItemBuilder.create()
                                .text("Delete")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {

                                                TreeItem<Object> objectTreeItem = mainGUIController.getSeletedItem();
                                                boolean remove = objectTreeItem.getParent().getChildren().remove(objectTreeItem);

                                            }
                                        }
                                )
                                .build()
                        // other menu item


                )
                .build();

        questionMenu
                = ContextMenuBuilder.create()
                .items(
                        MenuItemBuilder.create()
                                .text("Add Choice")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                                mainGUIController.makeBranch(new Choice(), mainGUIController.getSeletedItem());
                                            }
                                        }
                                )
                                .build(),
                        MenuItemBuilder.create()
                                .text("Move Up")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                ).disable(true)
                                .build(),
                        MenuItemBuilder.create()
                                .text("Move Down")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                ).disable(true)
                                .build(),
                        MenuItemBuilder.create()
                                .text("Properties")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }

                                        }
                                )
                                .build(),

                        MenuItemBuilder.create()
                                .text("Delete")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {

                                                TreeItem<Object> objectTreeItem = mainGUIController.getSeletedItem();
                                                boolean remove = objectTreeItem.getParent().getChildren().remove(objectTreeItem);

                                            }
                                        }
                                )
                                .build()
                        // other menu item


                )
                .build();

    }

    @Override
    public void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);


//        if (!empty && getTreeItem().isLeaf()) {
//            setContextMenu(null);
//            return;
//        }

        if(!empty && item instanceof Exam){
            setContextMenu(examMenu);
            return;
        }

        if(!empty && item instanceof Question){
            setContextMenu(questionMenu);
            return;
        }

        if(!empty && item instanceof Choice){
            setContextMenu(choiceMenu);
            return;
        }
//        setContextMenu(questionMenu);
    }
}
