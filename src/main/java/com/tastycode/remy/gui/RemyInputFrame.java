package com.tastycode.remy.gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.apache.log4j.Logger;

import com.tastycode.remy.dialogmanager.RemyDialog;
import com.tastycode.remy.reciperepository.HardcodedRecipeRepository;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class RemyInputFrame extends javax.swing.JFrame {
    /* log4j logger instance */
    private static final Logger logger = Logger.getLogger(RemyInputFrame.class
                    .getName());
    
    private JButton btn_say;
    private JTextField tf_usersaid;
    private JList recipeList;
    private AbstractAction action_previous;
    private JMenuItem menu_previous;
    private JMenuItem jMenuItem3;
    private AbstractAction menu_howmuch;
    private JMenuItem menu_for_howmuch;
    private AbstractAction action_whatnext = new AbstractAction("What's next?", null) {
        public void actionPerformed(ActionEvent evt) {
        }
    };
    private JMenuItem menu_settimer;
    private JMenu menu_say;
    private JScrollPane ingredientScroller;
    private JTextArea ta_ingredients;
    private JScrollPane jScrollPane3;
    private JTextArea ta_currentStep;
    private JScrollPane jScrollPane1;
    private AbstractAction action_refresh;
    private JButton btn_refresh;
    private AbstractAction action_selectRecipe;
    private JButton btn_ok;
    private JPanel panel_whichrecipe;
    private JDialog dialog_SelectRecipe;
    private JTextArea ta_timers;
    private JTextPane ta_steps;
    private JPanel panel_remyView;
    private JScrollPane jScrollPane2;
    private JPanel panel_talkPanel;
    private JTabbedPane tab_talk;
    private AbstractAction action_remyView;
    private JPanel jPanel1;
    private AbstractAction action_say;
    private AbstractAction action_start;
    private JMenuItem menu_start;
    private AbstractAction action_stop;
    private AbstractAction action_hear;
    private JButton button_hear;
    private JScrollPane jScrollPane4;
    private JLabel lb_speech;
    private JTextArea ta_speech;
    private JTextArea ta_currentIngredients;
    private AbstractAction action_WhatDo;
    private JMenuItem menu_whatDoIDo;
    private AbstractAction action_settimer;
    private JMenuItem menu_stop;
    private AbstractAction action_pause;
    private JMenuItem menu_pause;
    private AbstractAction action_open;
    private JMenuItem menu_open;
    private JMenuItem menu_remyView;
    private AbstractAction action_talkView;
    private JMenuItem menu_talkview;
    private JMenu menu_view;
    private JMenu menu_control;
    private JMenu jMenu1;
    private JMenuBar menu;
    private JTextArea ta_dialog;

    private RemyModel remyModel;

    /**
    * Auto-generated main method to display this JFrame
    */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RemyInputFrame inst = new RemyInputFrame(new RemyDialog(HardcodedRecipeRepository.getRatatouille()));
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }
    
    public RemyInputFrame(RemyDialog dialog) {
        super();
        remyModel = new RemyModel(dialog);
        initGUI();
        startRecipe();
    }
    
    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            BorderLayout thisLayout = new BorderLayout();
            getContentPane().setLayout(thisLayout);
            updateTitle();
            this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("remy.gif")).getImage());
            this.setEnabled(false);
            getContentPane().add(getTab_talk(), BorderLayout.CENTER);
            {
                menu = new JMenuBar();
                setJMenuBar(getMenu());
                menu.add(getJMenu1());
                menu.add(getMenu_control());
                menu.add(getMenu_view());
                menu.add(getMenu_say());
            }

            pack();
            this.setSize(547, 482);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void btn_sayActionPerformed(ActionEvent evt) {
        System.out.println("btn_say.actionPerformed, event="+evt);
        //TODO add your code for btn_say.actionPerformed
    }
    
    public JTextField getTf_usersaid() {
        if(tf_usersaid == null) {
            tf_usersaid = new JTextField();
            tf_usersaid.setBounds(12, 151, 510, 46);
            tf_usersaid.setText("What next?");
        }
        return tf_usersaid;
    }
    
    public JTextArea getTa_dialog() {
        if(ta_dialog == null) {
            ta_dialog = new JTextArea();
            ta_dialog.setBounds(44, 8, 355, 123);
            ta_dialog.setLineWrap(true);
            ta_dialog.setEnabled(false);
        }
        return ta_dialog;
    }

    public JMenuBar getMenu() {
        return menu;
    }
    
    private JMenu getJMenu1() {
        if(jMenu1 == null) {
            jMenu1 = new JMenu();
            jMenu1.setText("File");
            jMenu1.add(getMenu_open());
        }
        return jMenu1;
    }

    public JMenu getMenu_control() {
        if(menu_control == null) {
            menu_control = new JMenu();
            menu_control.setText("Control");
            menu_control.add(getMenu_start());
            menu_control.add(getMenu_pause());
            menu_control.add(getMenu_stop());
        }
        return menu_control;
    }

    private JMenu getMenu_view() {
        if(menu_view == null) {
            menu_view = new JMenu();
            menu_view.setText("View");
            menu_view.add(getMenu_talkview());
            menu_view.add(getMenu_remyView());
        }
        return menu_view;
    }
    
    private JMenuItem getMenu_talkview() {
        if(menu_talkview == null) {
            menu_talkview = new JMenuItem();
            menu_talkview.setText("menu_talkview");
            menu_talkview.setAction(getAction_talkView());
        }
        return menu_talkview;
    }
    
    public AbstractAction getAction_talkView() {
        if(action_talkView == null) {
            action_talkView = new AbstractAction("Talk", null) {
                public void actionPerformed(ActionEvent evt) {
                    logger.debug("switching to talk view");
                    final int TALK_INDEX = 0;
                    tab_talk.setSelectedIndex(TALK_INDEX);
                }
            };
        }
        return action_talkView;
    }
    
    private JMenuItem getMenu_remyView() {
        if(menu_remyView == null) {
            menu_remyView = new JMenuItem();
            menu_remyView.setText("menu_remyView");
            menu_remyView.setAction(getAction_remyView());
        }
        return menu_remyView;
    }
    
    public AbstractAction getAction_remyView() {
        if(action_remyView == null) {
            action_remyView = new AbstractAction("Remy", null) {
                public void actionPerformed(ActionEvent evt) {
                    logger.debug("switching to remy view");
                    final int REMY_INDEX = 1;
                    tab_talk.setSelectedIndex(REMY_INDEX);
                }
            };
        }
        return action_remyView;
    }
    
    private JMenuItem getMenu_open() {
        if(menu_open == null) {
            menu_open = new JMenuItem();
            menu_open.setText("menu_open");
            menu_open.setAction(getAction_open());
        }
        return menu_open;
    }
    
    public AbstractAction getAction_open() {
        if(action_open == null) {
            action_open = new AbstractAction("Open", null) {
                public void actionPerformed(ActionEvent evt) {
                    logger.info("open");
                    getDialog_SelectRecipe().setLocationRelativeTo(null);
                    getDialog_SelectRecipe().setVisible(true);
                }
            };
        }
        return action_open;
    }
    
    private JMenuItem getMenu_pause() {
        if(menu_pause == null) {
            menu_pause = new JMenuItem();
            menu_pause.setText("menu_pause");
            menu_pause.setAction(getAction_pause());
        }
        return menu_pause;
    }
    
    public AbstractAction getAction_pause() {
        if(action_pause == null) {
            action_pause = new AbstractAction("Pause", null) {
                public void actionPerformed(ActionEvent evt) {
                    logger.debug("pausing...");
                }
            };
        }
        return action_pause;
    }
    
    private JMenuItem getMenu_stop() {
        if(menu_stop == null) {
            menu_stop = new JMenuItem();
            menu_stop.setText("menu_stop");
            menu_stop.setAction(getAction_stop());
        }
        return menu_stop;
    }
    
    public AbstractAction getAction_stop() {
        if(action_stop == null) {
            action_stop = new AbstractAction("Stop", null) {
                public void actionPerformed(ActionEvent evt) {
                    logger.debug("stopping...");
                }
            };
        }
        return action_stop;
    }
    
    private JMenuItem getMenu_start() {
        if(menu_start == null) {
            menu_start = new JMenuItem();
            menu_start.setText("menu_start");
            menu_start.setAction(getAction_start());
        }
        return menu_start;
    }
    
    public AbstractAction getAction_start() {
        if(action_start == null) {
            action_start = new AbstractAction("Start", null) {
                public void actionPerformed(ActionEvent evt) {
                    logger.debug("starting...");
                }
            };
        }
        return action_start;
    }
    
    public AbstractAction getAction_say() {
        if(action_say == null) {
            action_say = new AbstractAction("Say", null) {
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("btn_say.actionPerformed, event="+evt);
                    //TODO add your code for btn_say.actionPerformed
                    System.out.println("said: " + tf_usersaid.getText());
                    String remySaid = remyModel.say(tf_usersaid.getText());
                  
                  String currentText = ta_dialog.getText();
                  currentText = currentText + 
                      "you: " + tf_usersaid.getText() + "\n";
                  currentText = currentText + 
                      "Remy: " + remySaid + "\n";
//                  
                  ta_dialog.setText(currentText);
                }
            };
        }
        return action_say;
    }
    
    private JPanel getJPanel1() {
        if(jPanel1 == null) {
            jPanel1 = new JPanel();
            jPanel1.setPreferredSize(new java.awt.Dimension(347, 74));
        }
        return jPanel1;
    }
    
    public JTabbedPane getTab_talk() {
        if(tab_talk == null) {
            tab_talk = new JTabbedPane();
            tab_talk.addTab("talk", null, getPanel_talkPanel(), null);
            tab_talk.addTab("remyView", null, getPanel_remyView(), null);
        }
        return tab_talk;
    }
    
    public JPanel getPanel_talkPanel() {
        if(panel_talkPanel == null) {
            panel_talkPanel = new JPanel();
            panel_talkPanel.setPreferredSize(new java.awt.Dimension(493, 282));
            panel_talkPanel.setLayout(null);
            panel_talkPanel.setSize(277, 100);
            panel_talkPanel.setDoubleBuffered(false);
            panel_talkPanel.add(getJScrollPane2());
            {
                btn_say = new JButton();
                panel_talkPanel.add(btn_say);
                panel_talkPanel.add(getTf_usersaid());
                panel_talkPanel.add(getJScrollPane4());
                panel_talkPanel.add(getLb_speech());
                panel_talkPanel.add(getButton_hear());
                btn_say.setLayout(null);
                btn_say.setText("Say");
                btn_say.setBounds(12, 203, 99, 35);
                btn_say.setAction(getAction_say());
            }
        }
        return panel_talkPanel;
    }
    
    private JScrollPane getJScrollPane2() {
        if(jScrollPane2 == null) {
            jScrollPane2 = new JScrollPane();
            jScrollPane2.setBounds(12, 8, 510, 133);
            getJScrollPane2().getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(14, 130));
            getJScrollPane2().getVerticalScrollBar().setAutoscrolls(true);
            getJScrollPane2().getHorizontalScrollBar().setAutoscrolls(true);
            jScrollPane2.setViewportView(getTa_dialog());
        }
        return jScrollPane2;
    }

    private JPanel getPanel_remyView() {
        if(panel_remyView == null) {
            panel_remyView = new JPanel();
            panel_remyView.setLayout(null);
            panel_remyView.setPreferredSize(new java.awt.Dimension(524, 450));
            panel_remyView.add(getJScrollPane1());
            panel_remyView.add(getTa_timers());
            panel_remyView.add(getBtn_refresh());
            panel_remyView.add(getJScrollPane3());
            panel_remyView.add(getIngredientScroller());
            panel_remyView.add(getTa_currentIngredients());
        }
        return panel_remyView;
    }
    
    public JTextPane getTa_steps() {
        if(ta_steps == null) {
            ta_steps = new JTextPane();
            ta_steps.setText("Steps");
            ta_steps.setBounds(69, 127, 301, 144);
            ta_steps.setPreferredSize(new java.awt.Dimension(246, 176));
        }
        return ta_steps;
    }
    
    public JTextArea getTa_timers() {
        if(ta_timers == null) {
            ta_timers = new JTextArea();
            ta_timers.setText("timer");
            ta_timers.setBounds(103, 325, 426, 35);
        }
        return ta_timers;
    }
    
    public JDialog getDialog_SelectRecipe() {
        if(dialog_SelectRecipe == null) {
            dialog_SelectRecipe = new JDialog(this);
            dialog_SelectRecipe.setTitle("Select Recipe");
            dialog_SelectRecipe.getContentPane().add(getPanel_whichrecipe(), BorderLayout.CENTER);
            dialog_SelectRecipe.setSize(239, 182);
        }
        return dialog_SelectRecipe;
    }
    
    public JPanel getPanel_whichrecipe() {
        if(panel_whichrecipe == null) {
            panel_whichrecipe = new JPanel();
            panel_whichrecipe.setLayout(null);
            panel_whichrecipe.add(getRecipeList());
            panel_whichrecipe.add(getBtn_ok());
        }
        return panel_whichrecipe;
    }
    
    public JList getRecipeList() {
        if(recipeList == null) {
            String [] recipes = remyModel.getRecipes();
            ListModel recipeListModel = 
                new DefaultComboBoxModel(
                    recipes);
            recipeList = new JList();
            recipeList.setModel(recipeListModel);
            recipeList.setBounds(41, 12, 135, 81);
        }
        return recipeList;
    }
    
    public JButton getBtn_ok() {
        if(btn_ok == null) {
            btn_ok = new JButton();
            btn_ok.setText("Ok");
            btn_ok.setBounds(81, 110, 53, 19);
            btn_ok.setAction(getAction_selectRecipe());
        }
        return btn_ok;
    }
    
    public AbstractAction getAction_selectRecipe() {
        if(action_selectRecipe == null) {
            action_selectRecipe = new AbstractAction("Ok", null) {
                public void actionPerformed(ActionEvent evt) {
                    logger.debug("selected recipe: " + (String)getRecipeList().getSelectedValue());
                    remyModel.setRecipe((String)getRecipeList().getSelectedValue());
                    RemyInputFrame.this.updateTitle();
                    getDialog_SelectRecipe().dispose();
                    startRecipe();
                }
            };
        }
        return action_selectRecipe;
    }
    
    protected void startRecipe() {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
        		remyModel.say("First step");
    		}
    	});
	}

	protected void updateTitle() {
    	
    	setTitle("Remy: " + remyModel.getCurrentRecipeName());
	}

	public JButton getBtn_refresh() {
        if(btn_refresh == null) {
            btn_refresh = new JButton();
            btn_refresh.setText("Refresh");
            btn_refresh.setBounds(4, 227, 94, 20);
            btn_refresh.setAction(getAction_refresh());
        }
        return btn_refresh;
    }
    
    public AbstractAction getAction_refresh() {
        if(action_refresh == null) {
            action_refresh = new AbstractAction("Refresh", null) {
                public void actionPerformed(ActionEvent evt) {
                    String stepsText = remyModel.getStepDescription();
                    String timerText = remyModel.getTimersDescription();
                    String current = remyModel.getCurrentStepDescription();
                    String ingredients = remyModel.getIngredientDescription();
                    String currentIngredient = remyModel.getCurrentIngredientDescription();
                    getTa_steps().setText(stepsText);
                    getTa_timers().setText(timerText);
                    getTa_currentStep().setText(current);
                    getTa_ingredients().setText(ingredients);
                    getTa_currentIngredients().setText(currentIngredient);
                }
            };
        }
        return action_refresh;
    }
    
    private JScrollPane getJScrollPane1() {
        if(jScrollPane1 == null) {
            jScrollPane1 = new JScrollPane();
            jScrollPane1.setBounds(4, 18, 335, 203);
            jScrollPane1.setViewportView(getTa_steps());
        }
        return jScrollPane1;
    }
    
    public JTextArea getTa_currentStep() {
        if(ta_currentStep == null) {
            ta_currentStep = new JTextArea();
            ta_currentStep.setText("CurrentStep");
            ta_currentStep.setBounds(103, 199, 237, 52);
            ta_currentStep.setPreferredSize(new java.awt.Dimension(392, 52));
            ta_currentStep.setLineWrap(true);
        }
        return ta_currentStep;
    }
    
    private JScrollPane getJScrollPane3() {
        if(jScrollPane3 == null) {
            jScrollPane3 = new JScrollPane();
            jScrollPane3.setBounds(103, 227, 426, 44);
            jScrollPane3.setViewportView(getTa_currentStep());
        }
        return jScrollPane3;
    }
    
    public JTextArea getTa_ingredients() {
        if(ta_ingredients == null) {
            ta_ingredients = new JTextArea();
            ta_ingredients.setText("Ingredients");
            ta_ingredients.setBounds(311, 20, 181, 173);
        }
        return ta_ingredients;
    }
    
    private JScrollPane getIngredientScroller() {
        if(ingredientScroller == null) {
            ingredientScroller = new JScrollPane();
            ingredientScroller.setBounds(345, 18, 184, 203);
            ingredientScroller.setViewportView(getTa_ingredients());
        }
        return ingredientScroller;
    }
    
    public JMenu getMenu_say() {
        if(menu_say == null) {
            menu_say = new JMenu();
            menu_say.setText("Say");
            menu_say.add(getMenu_for_howmuch());
            menu_say.add(getMenu_previous());
            menu_say.add(getMenu_settimer());
            menu_say.add(getMenu_whatDoIDo());
        }
        return menu_say;
    }
    
    private JMenuItem getMenu_for_howmuch() {
        if(menu_for_howmuch == null) {
            menu_for_howmuch = new JMenuItem();
            menu_for_howmuch.setText("menu_for_howmuch");
            menu_for_howmuch.setAction(getMenu_howmuch());
        }
        return menu_for_howmuch;
    }
    
    public AbstractAction getMenu_howmuch() {
        if(menu_howmuch == null) {
            menu_howmuch = new AbstractAction("How much", null) {
                public void actionPerformed(ActionEvent evt) {
                    getTf_usersaid().setText("How much ");
                }
            };
        }
        return menu_howmuch;
    }
    
    private JMenuItem getMenu_previous() {
        if(menu_previous == null) {
            menu_previous = new JMenuItem();
            menu_previous.setText("menu_previous");
            menu_previous.setAction(getAction_previous());
        }
        return menu_previous;
    }
    
    public AbstractAction getAction_previous() {
        if(action_previous == null) {
            action_previous = new AbstractAction("Previous step", null) {
                public void actionPerformed(ActionEvent evt) {
                    getTf_usersaid().setText("Previous step");
                }
            };
        }
        return action_previous;
    }
    
    private JMenuItem getMenu_settimer() {
        if(menu_settimer == null) {
            menu_settimer = new JMenuItem();
            menu_settimer.setText("menu_settimer");
            menu_settimer.setAction(getAction_settimer());
        }
        return menu_settimer;
    }
    
    public AbstractAction getAction_settimer() {
        if(action_settimer == null) {
            action_settimer = new AbstractAction("Set timer for", null) {
                public void actionPerformed(ActionEvent evt) {
                    getTf_usersaid().setText("Set timer for five minute.");
                }
            };
        }
        return action_settimer;
    }
    
    private JMenuItem getMenu_whatDoIDo() {
        if(menu_whatDoIDo == null) {
            menu_whatDoIDo = new JMenuItem();
            menu_whatDoIDo.setText("menu_whatDoIDo");
            menu_whatDoIDo.setAction(getAction_WhatDo());
        }
        return menu_whatDoIDo;
    }
    
    public AbstractAction getAction_WhatDo() {
        if(action_WhatDo == null) {
            action_WhatDo = new AbstractAction("What do I do with", null) {
                public void actionPerformed(ActionEvent evt) {
                    getTf_usersaid().setText("What do I do with");
                }
            };
        }
        return action_WhatDo;
    }
    
    public JTextArea getTa_currentIngredients() {
        if(ta_currentIngredients == null) {
            ta_currentIngredients = new JTextArea();
            ta_currentIngredients.setText("CurrentIngredients");
            ta_currentIngredients.setBounds(103, 279, 426, 40);
            ta_currentIngredients.setLineWrap(true);
        }
        return ta_currentIngredients;
    }
    
    public JTextArea getTa_speech() {
        if(ta_speech == null) {
            ta_speech = new JTextArea();
            ta_speech.setBounds(14, 267, 502, 94);
        }
        return ta_speech;
    }
    
    public JLabel getLb_speech() {
        if(lb_speech == null) {
            lb_speech = new JLabel();
            lb_speech.setText("Speech conversation");
            lb_speech.setBounds(12, 244, 222, 14);
        }
        return lb_speech;
    }
    
    private JScrollPane getJScrollPane4() {
        if(jScrollPane4 == null) {
            jScrollPane4 = new JScrollPane();
            jScrollPane4.setBounds(14, 269, 505, 97);
            jScrollPane4.setViewportView(getTa_speech());
        }
        return jScrollPane4;
    }
    
    public JButton getButton_hear() {
        if(button_hear == null) {
            button_hear = new JButton();
            button_hear.setText("Hear");
            button_hear.setBounds(122, 203, 79, 36);
            button_hear.setAction(getAction_hear());
        }
        return button_hear;
    }
    
    public AbstractAction getAction_hear() {
        if(action_hear == null) {
            action_hear = new AbstractAction("hear", null) {
                public void actionPerformed(ActionEvent evt) {
                    getTa_speech().setText(remyModel.getSpeakingDialogTranscript());
                }
            };
        }
        return action_hear;
    }

}
