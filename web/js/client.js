/* 
 * Comments Here
 */

//control one point, eliminate the other points by hovering your point over their exact center
//document.body.id = "theBody";
var player = "o"; //get player from server, hard code here for testing

function createChart(num) {
  require(["d3.min/d3", "dojo/dom-construct", "dojo/domReady!"], function (d3, domConstruct) {

    var mySVG = d3.select("#tictactoe").append("svg")
      .attr("width", 200)
      .attr("height", 200)
      .attr("id", "svg" + num)
      .style("border", "1px solid black")
      .style("margin-top","-5px")
      .on("click", function () {
        d3.select(this).style("background-image", "url('images/" + player + ".jpg')");
      });
  });
}

//resets the page
function reset(stringID) {
  document.getElementById(stringID).innerHTML = "";
}

//create the divs and buttons
require(["dojo/dom-construct", "dojo/dom", "dojo/domReady!"],
  function (domConstruct, dom, win) {
    var create = "";
    create = '<input id="user"/>';
    domConstruct.place(domConstruct.toDom(create), "start");
    create = '<input id="pwd" type="password"/>';
    domConstruct.place(domConstruct.toDom(create), "start");
    create = '<div id="button"></div>';
    domConstruct.place(domConstruct.toDom(create), "start");
    create = '<div id="resultDiv"></div>';
    domConstruct.place(domConstruct.toDom(create), "start");
    create = '<div id="tictactoe"></div>';
    domConstruct.place(domConstruct.toDom(create), "start");
    create = '<button id="login"></button>';
    domConstruct.place(domConstruct.toDom(create), "button");
    create = '<button id="create"></button>';
    domConstruct.place(domConstruct.toDom(create), "button");
    create = '<button id="online"></button>';
    domConstruct.place(domConstruct.toDom(create), "button");
    create = '<button id="ai"></button>';
    domConstruct.place(domConstruct.toDom(create), "button");
    create = '<button id="logout"></button>';
    domConstruct.place(domConstruct.toDom(create), "button");
  });

//attach stuff to buttons
require(["dijit/form/Button", "dojo/dom", "dojo/domReady!"], function (Button, dom) {
  var button1 = new Button({
    iconClass: "dijitIconNewTask",
    showLabel: true,
    label: "Login",
    onClick: function (evt) {
        u = dom.byId("user").value;
                    p = dom.byId("pwd").value;
                    // prevent the page from navigating after submit
                    evt.stopPropagation();
                    evt.preventDefault();
                    //var external = "http://bost.ocks.org/mike/drought/pdsi.json";
                    //var internal = "data/sample.json";
                    post_json(rest,u,p); //call to dojo AJAX REST service
    }
  }, "login").startup();
  
  var button2 = new Button({
    iconClass: "dijitIconNewTask",
    showLabel: true,
    label: "Create Account",
    onClick: function () {
      //chcek DB for existing user
      //confirm password
      //send user/pass to DB
    }
  }, "create").startup();
  
  var button3 = new Button({
    iconClass: "dijitIconNewTask",
    showLabel: true,
    label: "Find Online Opponent",
    onClick: function () {
      reset("tictactoe");
      for (i = 0; i < 9; i++) {
        createChart(i);
      }
    }
  }, "online").startup();
  
  var button4 = new Button({
    iconClass: "dijitIconNewTask",
    showLabel: true,
    label: "Face AI Opponent",
    onClick: function () {
      reset("tictactoe");
      for (i = 0; i < 9; i++) {
        createChart(i);
      }
    }
  }, "ai").startup();
  
  var button5 = new Button({
    iconClass: "dijitIconNewTask",
    showLabel: true,
    label: "Logout",
    onClick: function () {
        reset("tictactoe");
    }
  }, "logout").startup();
});

//AMD dojo AJAX
function post_json(url,uid,pwd){
    require(["dojo/json","dojo/dom", "dojo/on", "dojo/request", "dojo/domReady!"],
        function(JSON, dom, on, request){
            //console.log(data);
            // Request with some data input
            {   
                headers:{'X-Requested-With': null,
        		'Content-Type': 'text/plain'},
                data: JSON.stringify(data),
                timeout: 3000     
            }).then(
                function(response){
                    // Display the text file content
                    dom.byId("resultDiv").innerHTML = "<pre>"+JSON.parse(response)+"</pre>";
                },
                function(error){
                    // Display the error returned
                    dom.byId("resultDiv").innerHTML = "<div class=\"error\">"+error+"<div>";
                }
            );
        });
}

/* Beckers pre code
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

*/