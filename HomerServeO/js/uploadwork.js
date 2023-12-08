let form=document.getElementById("form")
form.onsubmit=(e)=>{
  e.preventDefault();
  let work=document.getElementById("type").value
  let doorno=document.getElementById("DoorNumber").value
let street=document.getElementById("Street").value
let landmark=document.getElementById("Landmark").value
let district=document.getElementById("District").value
let pincode=document.getElementById("pincode").value
let state=document.getElementById("State").value


if(doorno.value===""){
  doorno=localStorage.getItem("door number")
}
if(street.value===""){
  street=localStorage.getItem('street')
}
if(landmark.value===""){
  landmark=localStorage.getItem("landmark")
}
if(district.value===""){
  district=localStorage.getItem("district")
}
if(pincode.value===""){
  pincode=localStorage.getItem("pin-code")
}
if(state.value===""){
  state=localStorage.getItem("state")
}



var obj={
  type:work,

  address:{
     d_no:doorno,
     street:street,
     landmark:landmark,
     district:district,
     state:state,
     pinCode:pincode
  }
  
}

fetch(`http://localhost:8080/work?cus_id=${localStorage.getItem("id")}`, {
method: 'POST',
headers: {
  'Content-Type': 'application/json'
},
body: JSON.stringify(obj)
})
.then(response => response.json())
.then(obj => {
  
  console.log(obj);

  if(obj.status===201){
    window.location.href="http://127.0.0.1:5500/customerpage.html"
  }

 
 
})
.catch(error => {

  console.error("error")
});

}


