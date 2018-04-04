var database = firebase.database();
var tables = database.ref("date");
var Timetable=[];
var colours=["#F44336", "#4CAF50", "#F9A825", "#FF5722", "#512DA8","#D81B60","#880E4F","#00796B"];
var colourcount=0;
var today={};




tables.on('value', function(snapshot) {
	table=snapshot.val();
	date = document.getElementById("date").value;
	console.log(table[date]);
    display(table[date]);
    });
    
function display(today)
    {
		document.getElementById('scheduleList').innerHTML='';
		for (x in today)
			{
				var colourcode=(colourcount++)%8;				
				console.log(today[x]);
				var div=document.createElement("div");
				var text=document.createTextNode(today[x]);
				div.appendChild(text);
				div.style.height="100px";
				div.style.padding="35px"
				div.style.textAlign="center";	
				div.style.display="display: flex;"			
				console.log(colourcode);
				console.log(colours[colourcode]);
				div.style.backgroundColor=colours[colourcode];
				div.style.color="white";				
				document.getElementById("scheduleList").appendChild(div);
			}


		
	}


function setPeriod() {
    today.p1=document.getElementById("1").value; 
    today.p2=document.getElementById("2").value; 
    today.p3=document.getElementById("3").value; 
	today.p4=document.getElementById("4").value; 
    today.p5=document.getElementById("5").value; 	
	today.p6=document.getElementById("6").value; 
    today.p7=document.getElementById("7").value; 
	today.p8=document.getElementById("8").value;
	console.log(today);
	update();
}




function update()
	{
		date = document.getElementById("date").value;
		console.log(date);
		firebase.database().ref('date/' + date).set(today);
		Metro.toast.create("Updated", null, 5000, "bg-green fg-white");
	}
