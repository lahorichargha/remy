package com.tastycode.remy;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.annotation.Resource;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tastycode.remy.dialogmanager.RemyDialog;
import com.tastycode.remy.dialogmanager.SpeakerAndListener;
import com.tastycode.remy.gui.RemyInputFrame;

public class Remy implements Runnable, ApplicationContextAware {
	@Resource private SpeakerAndListener speaker;
	@Resource private RemyDialog dialog;
	@Resource private RemyInputFrame gui;
	private ConfigurableApplicationContext context;	

	public Remy() {
	}
	
	public void run() {
        startGui();
		
		speaker.startDialog(dialog);
		System.out.println("Stopping dialog:");
	}

	protected void startGui() {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui.setLocationRelativeTo(null);
                gui.setVisible(true);
            }
        });
		gui.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent windowevent) {
				Remy.this.windowClosed(windowevent);
			}
		});
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"/context/Remy-context.xml"
		}, Remy.class);
		Remy remy = (Remy) context.getBean("remy");
		remy.run();
	}
	
	private static Logger logger = Logger.getLogger(Remy.class);

	public void setApplicationContext(ApplicationContext applicationcontext)
			throws BeansException {
		if (applicationcontext instanceof ConfigurableApplicationContext) {
			this.context = (ConfigurableApplicationContext) applicationcontext;
		}
	}

	protected void windowClosed(WindowEvent windowevent) {
		if (context != null)
			context.close();
		logger.info("Closing Remy");
	}
}
