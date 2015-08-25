package org.zkoss.progressbar;

import java.io.IOException;

import org.zkoss.progressbar.events.ChangedEvent;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;

public class Progressbar extends AbstractComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int _value = 0;
	private boolean _disabled = false;
	
	private class ProgressbarEvents {
		public static final String ONVALUECHANGED = "onValueChanged";
	}
	
	static {
		addClientEvent(Progressbar.class, "onValueChanged", CE_IMPORTANT);
	}
	
	public Progressbar() {
		super();
	}
	
	public int getValue() {
		return _value;
	}
	
	public void setValue(int value) {
		
		//if disabled do not do anything
		if(_disabled) return;
		
		if(value < 0 || value > 100) {
			throw new UiException("Value is out of range, please use a value between 0 and 100");
		}
		
		if(_value != value) {
			_value = value;
			smartUpdate("value", value);
		}
	}
	
	public boolean getDisabled() {
		return _disabled;
	}
	
	public void setDisabled(boolean b) {
		if(b != _disabled) {
			_disabled = b;
			smartUpdate("disabled", b);
		}
	}
	
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
		
		if (cmd.equals(ProgressbarEvents.ONVALUECHANGED)) {
			ChangedEvent evt = ChangedEvent.getChangedEvent(request);
			Events.postEvent(evt);
		} else
			super.service(request, everError);
	}	
	
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
			throws IOException {
		
		super.renderProperties(renderer);
	    
		renderer.render("value", _value);
	    renderer.render("disabled", _disabled);
	}
	
}
