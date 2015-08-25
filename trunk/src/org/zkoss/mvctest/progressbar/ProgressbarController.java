package org.zkoss.mvctest.progressbar;

import org.zkoss.progressbar.Progressbar;
import org.zkoss.progressbar.events.ChangedEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class ProgressbarController extends GenericForwardComposer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2879311556515663251L;
	
	Progressbar prog;
	
	public void onValueChanged$prog(ChangedEvent e) {
		System.out.println(e);
	}
}
