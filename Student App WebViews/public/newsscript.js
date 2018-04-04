var database = firebase.database();
var news= database.ref("News");
var newslist=[];
var colours=["teal lighten-2", "purple lighten-1", "pink lighten-1", "orange", "red darken-2"];
var colourcount=0;


news.on('value', function(snapshot) {
    newslist=snapshot.val();
    display();
    });

display();
function display()
    {
        document.getElementById("dynamic").innerHTML='';
        colourcount=0;
        for (x in newslist)
            {
            var colourcode=(colourcount++)%5;
            var para = document.createElement("p");
            var node = document.createTextNode(newslist[x].body);
            para.appendChild(node);

            var span = document.createElement("span");
            span.className +="card-title";
            var text = document.createTextNode(newslist[x].title);  
            span.appendChild(text);

            var div2 = document.createElement('div');
            div2.className +="card-content white-text";
            
            var div1 = document.createElement('div');
            div1.className +="card "+colours[colourcode];

            var element = document.getElementById("dynamic");
            
            
            div2.appendChild(span);
            div2.appendChild(para);
            
            div1.appendChild(div2);
            element.appendChild(div1);
            }
    }

