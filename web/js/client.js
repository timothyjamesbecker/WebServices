/* 
 * Comments Here
 */

//AMD dojo Buttons
//dojo button attached to D3 function via AMD
require(["dijit/form/Button", "dijit/form/ValidationTextBox",
         "dojo/dom","dojo/dom-construct",
         "dojo/dom-class","dojo/domReady!"],
    function(Button,ValidationTextBox,dom,con,cl) {
        var btn1 = con.create("button",{Id:"btn1"},"start");
        var in1  = con.create("input",{Id:"uid"},"start");
        var in2  = con.create("input",{Id:"pwd"},"start");
        var res = con.create("div",{Id:"resultDiv"},"start");
        var div  = con.create("div",{Id:"gfx"},"start");
        
        var uid_t = new ValidationTextBox({
                name: "user",
                type: "text"
        }, "uid");
        
        var pwd_t = new ValidationTextBox({
                name: "password",
                type: "password"
        }, "pwd");     
        
        var button1 = new Button({
		iconClass:"dijitIconDatabase",
		showLabel:true,
		label: "JSON", // analogous to title when showLabel is false
		onClick: function(evt){
                    u = dom.byId("uid").value;
                    p = dom.byId("pwd").value;
                    // prevent the page from navigating after submit
                    evt.stopPropagation();
                    evt.preventDefault();
                    var rest     = "http://localhost:8084/Project/rest/";
                    post_json(rest,u,p); //call to dojo AJAX REST service
                }
	}, "btn1").startup();
        
});

//AMD dojo get form strings with pwd blocker?

//AMD dojo AJAX
function get_json(url){
    require(["dojo/json","dojo/dom", "dojo/on", "dojo/request", "dojo/domReady!"],
        function(JSON, dom, on, request){
            // Request the text file
            request.get(url,
            {
                headers:{'X-Requested-With': null,
        		'Content-Type': 'application/json'}
            }).then(
                function(response){
                    // Display the text file content
                    dom.byId("resultDiv").innerHTML = "<pre>"+response+"</pre>";
                },
                function(error){
                    // Display the error returned
                    dom.byId("resultDiv").innerHTML = "<div class=\"error\">"+error+"<div>";
                }
            );
        });
}

//AMD dojo AJAX
function post_json(url,uid,pwd){
    require(["dojo/json","dojo/dom", "dojo/on", "dojo/request", "dojo/domReady!"],
        function(JSON, dom, on, request){
            var data = {"uid":uid,"name":pwd};  //access the hash
            //console.log(data);
            // Request with some data input
            request.post(url+"data.table",
            {   
                headers:{'X-Requested-With': null,
                         'Content-Type': 'application/json'},
                data: JSON.stringify(data),
                timeout: 3000     
            }).then(
                function(response){
                    // Display the text file content
                    var obj = JSON.parse(response);
                    console.log(obj);
                    //obj.uid_pwd_hash
                    dom.byId("resultDiv").innerHTML = "<pre>"+obj+"</pre>";
                },
                function(error){
                    // Display the error returned
                    dom.byId("resultDiv").innerHTML = "<div class=\"error\">"+error+"<div>";
                }
            );
        });
}