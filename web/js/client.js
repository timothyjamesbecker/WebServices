/* 
 * Comments Here
 */

//control one point, eliminate the other points by hovering your point over their exact center
//document.body.id = "theBody";
var svg_array = ["","","","","","","","",""];
var player = "x";//for ai
var turn_number = 1;
var play_chosen = 0;

function easyAI(){
    console.log(svg_array);
    player = "o";
  if(!checkWinner("x")){
    var place = Math.floor(Math.random()*10);
    var filled = true; 
    for(i=0;i<10;i++){
      if(svg_array[i]==""){
        filled = false;
      }
    }
    if(filled==false){
      if(svg_array[place]==""){
        document.getElementById("svg" + place).style.backgroundImage = ("url('images/" + player + ".jpg')");
        svg_array[place] = "o";
        player = "x";
        checkWinner("o");
      }
      else{
        easyAI()
      }
    }
  }
}

function hardAI(){
  var place = 0;
  player = "o";
  if(!checkWinner("x")){
    var filled = true; 
    for(i=0;i<10;i++){
      if(svg_array[i]==""){
        filled = false;
      }
    }
    if(filled==false){//still spaces on the board
      if(testVictory("o")!=9){//victory
        place = testVictory("o");
        document.getElementById("svg" + place).style.backgroundImage = ("url('images/" + player + ".jpg')");
        svg_array[place] = "o";
      }
      else if(testVictory("x")!=9){//could lose must block x
        place = testVictory("x");
        document.getElementById("svg" + place).style.backgroundImage = ("url('images/" + player + ".jpg')");
        svg_array[place] = "o";
      }
      else{
        if(turn_number==1&&svg_array[4]!="x"){//which play path to use
          play_chosen="1";
        }
        if(play_chosen==0){//player took the center
          place = Math.floor(Math.random()*4);
          if(turn_number==1){//take top right corner
            if(place==0){
                document.getElementById("svg0").style.backgroundImage = ("url('images/" + player + ".jpg')");
            	svg_array[0] = "o";
            }
            else if(place==1){
                document.getElementById("svg2").style.backgroundImage = ("url('images/" + player + ".jpg')");
            	svg_array[2] = "o";
            }
            else if(place==2){
                document.getElementById("svg6").style.backgroundImage = ("url('images/" + player + ".jpg')");
            	svg_array[6] = "o";
            }
            else{
                document.getElementById("svg8").style.backgroundImage = ("url('images/" + player + ".jpg')");
            	svg_array[8] = "o";
            }
          }
          else if(turn_number==2){
            if(svg_array[0]=="o"&&svg_array[8]=="x"){
                document.getElementById("svg2").style.backgroundImage = ("url('images/" + player + ".jpg')");
            	svg_array[2] = "o";
            }
            else if(svg_array[2]=="o"&&svg_array[6]=="x"){
                document.getElementById("svg0").style.backgroundImage = ("url('images/" + player + ".jpg')");
            	svg_array[0] = "o";
            }
            else if(svg_array[6]=="o"&&svg_array[2]=="x"){
                document.getElementById("svg8").style.backgroundImage = ("url('images/" + player + ".jpg')");
            	svg_array[8] = "o";
            }
            else if(svg_array[8]=="o"&&svg_array[0]=="x"){
                document.getElementById("svg6").style.backgroundImage = ("url('images/" + player + ".jpg')");
            	svg_array[6] = "o";
            }
          }
          else{easyAI();}
        }
        else if(play_chosen==1){//player did not take the center
          if(turn_number==1){
              document.getElementById("svg4").style.backgroundImage = ("url('images/" + player + ".jpg')");
              svg_array[4] = "o";
          }
          else if(turn_number==2){
              place = Math.floor(Math.random()*4);
            if(place==0&&svg_array[1]==""){
                document.getElementById("svg1").style.backgroundImage = ("url('images/" + player + ".jpg')");
                svg_array[1] = "o";
            }
            else if(place==1&&svg_array[3]==""){
                document.getElementById("svg3").style.backgroundImage = ("url('images/" + player + ".jpg')");
            	svg_array[3] = "o";
            }
            else if(place==2&&svg_array[5]==""){
                document.getElementById("svg5").style.backgroundImage = ("url('images/" + player + ".jpg')");
            	svg_array[5] = "o";
            }
            else if(place==3&&svg_array[7]==""){
                document.getElementById("svg7").style.backgroundImage = ("url('images/" + player + ".jpg')");
            	svg_array[7] = "o";
            }
            else{
                hardAI();
            }
          }
          else{easyAI();}
        }
        else{
          easyAI();
        }
      }
    }
  }
      turn_number++;
      player = "x";
      checkWinner("o");
}

function testVictory(player){
  //horizontal top
  if(svg_array[0]==player&&svg_array[1]==player&&svg_array[2]==""){
    return 2;
  }
  else if(svg_array[0]==player&&svg_array[2]==player&&svg_array[1]==""){
    return 1;
  }
  else if(svg_array[2]==player&&svg_array[1]==player&&svg_array[0]==""){
    return 0;
  }
  //middle horizontal
  if(svg_array[3]==player&&svg_array[4]==player&&svg_array[5]==""){
    return 5;
  }
  else if(svg_array[3]==player&&svg_array[5]==player&&svg_array[4]==""){
    return 4;
  }
  else if(svg_array[4]==player&&svg_array[5]==player&&svg_array[3]==""){
    return 3;
  }
  //bottom horizontal
  if(svg_array[6]==player&&svg_array[7]==player&&svg_array[8]==""){
    return 8;
  }
  else if(svg_array[6]==player&&svg_array[8]==player&&svg_array[7]==""){
    return 7;
  }
  else if(svg_array[7]==player&&svg_array[8]==player&&svg_array[6]==""){
    return 6;
  }
  //left vertical
  if(svg_array[0]==player&&svg_array[3]==player&&svg_array[6]==""){
    return 6;
  }
  else if(svg_array[0]==player&&svg_array[6]==player&&svg_array[3]==""){
    return 3;
  }
  else if(svg_array[3]==player&&svg_array[6]==player&&svg_array[0]==""){
    return 0;
  }
  //middle vertical
  if(svg_array[1]==player&&svg_array[4]==player&&svg_array[7]==""){
    return 7;
  }
  else if(svg_array[1]==player&&svg_array[7]==player&&svg_array[4]==""){
    return 4;
  }
  else if(svg_array[4]==player&&svg_array[7]==player&&svg_array[1]==""){
    return 1;
  }
  //right vertical
  if(svg_array[2]==player&&svg_array[5]==player&&svg_array[8]==""){
    return 8;
  }
  else if(svg_array[2]==player&&svg_array[8]==player&&svg_array[5]==""){
    return 5;
  }
  else if(svg_array[5]==player&&svg_array[8]==player&&svg_array[2]==""){
    return 2;
  }
  //diagonal down right
  if(svg_array[0]==player&&svg_array[4]==player&&svg_array[8]==""){
    return 8;
  }
  else if(svg_array[0]==player&&svg_array[8]==player&&svg_array[4]==""){
    return 4;
  }
  else if(svg_array[4]==player&&svg_array[8]==player&&svg_array[0]==""){
    return 0;
  }
  //diagonal down left
  if(svg_array[2]==player&&svg_array[4]==player&&svg_array[6]==""){
    return 6;
  }
  else if(svg_array[2]==player&&svg_array[6]==player&&svg_array[4]==""){
    return 4;
  }
  else if(svg_array[4]==player&&svg_array[6]==player&&svg_array[2]==""){
    return 2;
  }
  else{
    return 9;
  }
}

function resetVariables(){
  svg_array = ["","","","","","","","",""];
	player = "x";
	turn_number = 1;
	play_chosen = 0;
}

function checkWinner(player){
  if(svg_array[1]==player){
    if(svg_array[0]==player&&svg_array[2]==player){
      document.getElementById("tictactoe").innerHTML += "player " + player + " has won";
      return true;
    }
    else if(svg_array[4]==player&&svg_array[7]==player){
      document.getElementById("tictactoe").innerHTML += "player " + player + " has won";
      return true;
    }
  }
  if(svg_array[3]==player){
    if(svg_array[0]==player&&svg_array[6]==player){
      document.getElementById("tictactoe").innerHTML += "player " + player + " has won";
      return true;
    }
    else if(svg_array[4]==player&&svg_array[5]==player){
      document.getElementById("tictactoe").innerHTML += "player " + player + " has won";
      return true;
    }
  }
  if(svg_array[5]==player){
    if(svg_array[2]==player&&svg_array[8]==player){
      document.getElementById("tictactoe").innerHTML += "player " + player + " has won";
      return true;
    }
  }
  if(svg_array[7]==player){
    if(svg_array[6]==player&&svg_array[8]==player){
      document.getElementById("tictactoe").innerHTML += "player " + player + " has won";
      return true;
    }
  }
  if(svg_array[4]==player){
    if(svg_array[0]==player&&svg_array[8]==player){
      document.getElementById("tictactoe").innerHTML += "player " + player + " has won";
      return true;
    }
    else if(svg_array[2]==player&&svg_array[6]==player){
      document.getElementById("tictactoe").innerHTML += "player " + player + " has won";
      return true;
    }
  }
  for(i=0;i<10;i++){
    if(svg_array[i]==""){
      return false;
    }
  }
  document.getElementById("tictactoe").innerHTML = "Draw, click button to play again";
  return true;
}

function createChart(num, difficulty) {
  require(["d3.min/d3", "dojo/dom-construct", "dojo/domReady!"], function (d3, domConstruct) {

    var mySVG = d3.select("#tictactoe").append("svg")
      .attr("width", 200)
      .attr("height", 200)
      .attr("id", "svg" + num)
      .style("border", "1px solid black")
      .style("margin-top","-5px")
      .on("click", function () {
        if(svg_array[this.id.charAt(3)]!=""){
          //do nothing
        }
        else{
          d3.select(this).style("background-image", "url('images/" + player + ".jpg')");
          svg_array[this.id.charAt(3)] = "x";
          if(difficulty==1){
              hardAI();
          }
          else{
              easyAI();
          }
        }
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
    var create = '<header><img src="images/tictactoe.jpg" id="banner"></header>';
    domConstruct.place(domConstruct.toDom(create), "wrapper");
    create = '<div id="userlogin"></div>';
    domConstruct.place(domConstruct.toDom(create), "wrapper");
    create = '<label id="username">User Name:<input id="user"/></label>';
    domConstruct.place(domConstruct.toDom(create), "userlogin");
    create = '<label id="password">Password:<input id="pwd" type="password"/></label>';
    domConstruct.place(domConstruct.toDom(create), "userlogin");
    create = '<div id="button"></div>';
    domConstruct.place(domConstruct.toDom(create), "wrapper");
    create = '<div id="resultDiv"></div>';
    domConstruct.place(domConstruct.toDom(create), "wrapper");
    create = '<div id="tictactoe"></div>';
    domConstruct.place(domConstruct.toDom(create), "wrapper");
    create = '<button id="login"></button>';
    domConstruct.place(domConstruct.toDom(create), "button");
    create = '<button id="create"></button>';
    domConstruct.place(domConstruct.toDom(create), "button");
    create = '<button id="online"></button>';
    domConstruct.place(domConstruct.toDom(create), "button");
    create = '<button id="ai"></button>';
    domConstruct.place(domConstruct.toDom(create), "button");
    create = '<button id="easyai"></button>';
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
                    action = "login";
                    // prevent the page from navigating after submit
                    evt.stopPropagation();
                    evt.preventDefault();
                    //var external = "http://bost.ocks.org/mike/drought/pdsi.json";
                    //var internal = "data/sample.json";
                    var rest     = "http://localhost:8084/Project/apple/generic";
                    post_json(rest,action,u,p); //call to dojo AJAX REST service
    }
  }, "login").startup();
  
  var button2 = new Button({
    iconClass: "dijitIconNewTask",
    showLabel: true,
    label: "Create Account",
    onClick: function (evt) {
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
    label: "Face Hard AI",
    onClick: function () {
      reset("tictactoe");
      resetVariables();
      for (i = 0; i < 9; i++) {
        createChart(i,1);
      }
    }
  }, "ai").startup();
  
  var button5 = new Button({
    iconClass: "dijitIconNewTask",
    showLabel: true,
    label: "Face Easy AI",
    onClick: function () {
      reset("tictactoe");
      resetVariables();
      for (i = 0; i < 9; i++) {
        createChart(i,0);
      }
    }
  }, "easyai").startup();
  
  var button6 = new Button({
    iconClass: "dijitIconNewTask",
    showLabel: true,
    label: "Logout",
    onClick: function () {
        reset("tictactoe");
    }
  }, "logout").startup();
});

//AMD dojo AJAX
function post_json(url,action,uid,pwd){
    require(["dojo/json","dojo/dom", "dojo/on", "dojo/request", "dojo/domReady!"],
        function(JSON, dom, on, request){
            var data = { action:action, uid: uid, pwd: pwd};  //access the hash
            //console.log(data);
            // Request with some data input
            request.post(url,
            {   
                headers:{'X-Requested-With': null,
        		'Content-Type': 'text/plain'},
                data: JSON.stringify(data),
                timeout: 3000     
            }).then(
                function(response){
                    // Display the text file content
                   // dom.byId("resultDiv").innerHTML = "<pre>"+JSON.parse(response)+"</pre>";
                },
                function(error){
                    // Display the error returned
                   // dom.byId("resultDiv").innerHTML = "<div class=\"error\">"+error+"<div>";
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