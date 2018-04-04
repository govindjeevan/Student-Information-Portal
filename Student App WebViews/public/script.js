var database = firebase.database();
var questions= database.ref("questions");
var question=[];
var colours=["teal lighten-2", "purple lighten-1", "pink lighten-1", "orange", "red darken-2"];
var colourcount=0;


$(document).ready(function(){
    $('ul.tabs').tabs({
      swipeable : true,
      responsiveThreshold : 1920
    });
  });

  function submit()
    {
        console.log("hey");        
        var username="Govind";
        var title=document.getElementById("Question").value;
        var body=document.getElementById("Description").value;
        var opt1=document.getElementById("opt1").value;
        var opt2=document.getElementById("opt2").value;
        
        console.log(title);
        newQuestion(username, title, body, opt1, opt2);
    }
function display()
    {
        document.getElementById("dynamic").innerHTML='';
        colourcount=0;
        for (x in question)
            {
            var colourcode=(colourcount++)%5;
            var para = document.createElement("p");
            var node = document.createTextNode(question[x].body);
            para.appendChild(node);

            var span = document.createElement("span");
            span.className +="card-title";
            var text = document.createTextNode(question[x].title);  
            span.appendChild(text);

            var div2 = document.createElement('div');
            div2.className +="card-content white-text";
            
            var div2_1=document.createElement('div');
            div2_1.className+="card-action";
            var one = document.createElement("a");
            var two = document.createElement("a");
            one.className="waves-effect waves-light btn-small";
            two.className="waves-effect waves-light btn-small";            
//            one.innerHTML=question[x].opt1;
  //          two.innerHTML=question[x].opt2;
            one.innerHTML=question[x].opt1+"\n"+question[x].vote1;
            one.id="1"+x
            two.innerHTML=question[x].opt2+"\n"+question[x].vote2;
            two.id="2"+x
            div2_1.appendChild(one);
            div2_1.appendChild(two);
            var result=document.createTextNode(question[x].vote1+" "+question[x].vote2);
  
            
            var div1 = document.createElement('div');
            div1.className +="card "+colours[colourcode];


            var element = document.getElementById("dynamic");
            
            
            div2.appendChild(span);
            div2.appendChild(para);


            //div2.appendChild(result);            
            div1.appendChild(div2);
            div1.appendChild(div2_1);
            element.appendChild(div1);
            }

        
            var buttons= document.getElementsByTagName('a');
            console.log(buttons.length);
            for (var i = 0; i < buttons.length; i++) {
                var button = buttons[i];
                button.onclick = function() {
                    console.log(this.id);
                    id=this.id;
                    console.log(id);
                    var opt=id[0];
                    id=id.slice(1,id.length);
                    console.log(id);
                    firebase.database().ref('/questions/' + id).once('value').then(function(snapshot) {
                        var question = (snapshot.val());
                        if(opt==1)
                        {
                            question.vote1++;
                        }
                        if(opt==2)
                        {
                            question.vote2++;
                        }
                        console.log(question);
                        var updates = {};
                        updates['/questions/' + id] = question;
                        return firebase.database().ref().update(updates);  
                        
                    })

                    
                    
                    
            }
    }
    }

questions.on('value', function(snapshot) {
    question=snapshot.val();
    display();
    });
    




function newQuestion(username, title, body, opt1, opt2) {
    // A post entry.
    var postData = {
      author: username,
      body: body,
      title: title,
      opt1: opt1,
      opt2: opt2,
      vote1: 0,
      vote2: 0
    };
    // Get a key for a new Post.
    var newPostKey = firebase.database().ref().child('questions').push().key;
    // Write the new post's data simultaneously in the posts list and the user's post list.
    console.log(newPostKey);
    var updates = {};
    updates['/questions/' + newPostKey] = postData;
    return firebase.database().ref().update(updates);
  }



