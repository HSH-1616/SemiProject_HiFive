
/*
if(document.querySelector(".findIdValue").innerHTML.length)

*/

const len = document.querySelector(".findIdValue").innerHTML.length;

if(len > 6){
	const id=document.querySelector(".findIdValue").innerHTML.substring(1,len-1);
	document.querySelector(".findIdValue").innerHTML = "*"+id+"*";
}






