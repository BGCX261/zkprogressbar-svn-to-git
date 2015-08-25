zk.$package('progressbar');

progressbar.Progressbar = zk.$extends(zul.Widget, {
	
	
	$define: {					
		value: 	    function () {
						if(this.uuid != '') {
							var node = $('#' + this.uuid).children('#mainProgress');
					
							if(node) {
								jq(node).progressbar('option', 'value', this._value);
							}
						}
					},

		disabled: [ 
					function (v) {
			        	return !v ? false : v;
			        },
			               
			        function () {
			        	this._disable(this._disabled);
			        }
		           
		           ]
							
	},
	
	
	
	bind_:function() {
		this._init();
		
		this.$supers('bind_', arguments);
	},
	
	unbind_:function() {
		
		var node = $('#' + this.uuid).children('#mainProgress');
		
		if(node) {
			node.progressbar('destroy');
		}
		
		this.$supers('unbind_', arguments);
	},
	
	redraw: function (out) {			
		out.push('<div id="' + this.uuid + '"><div id="mainProgress"></div></div>');
	},
	
	_init: function() {	
		
		console.log("init function");
		
		var node = $('#' + this.uuid).children('#mainProgress');
		
		if(node) {
			console.log("updating node");
			
			//initialize the progressbar's value
			node.progressbar({value: this._value});
			
			//bind the onchange event
			node.bind('progressbarchange', this._onValueChanged);
		}
		
		//set whether or not it is disabled
		this._disable(this._disabled);
	},
	
	_disable: function(b) {
		var node = $('#' + this.uuid).children('#mainProgress');
    	
    	if(node) {
			node.progressbar('option', 'disabled', this._disabled);
		}
	},
	
	_onValueChanged: function(event, ui) {
		//note that "this" changes its scope here, it now refers
		//to the div tag of the progress control and not the JS object
		var widget = zk.Widget.$(this.parentNode);
		//be careful when using this
		//returning undefined screws it up
		widget.fire('onValueChanged', {value:widget._value});
	}
});