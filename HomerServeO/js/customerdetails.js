let name=document.getElementById("name")
let email=document.getElementById("email")
let phone=document.getElementById("phone")
let familyCount=document.getElementById("familycount")
let address=document.getElementById("fullAddress")
console.log(name);

window.fetch(`http://localhost:8080/customer?id=${localStorage.getItem("id")}`,{
    method:"GET"
})
.then((x)=>x.json())
.then((obj)=>{
    console.log(obj);

    if(obj.status===302){
        name.textContent=obj.data.name
        email.textContent=obj.data.email
        phone.textContent=obj.data.phone
        familyCount.textContent=obj.data.familyCount
        let add=obj.data.address
        address.textContent=`${add.d_no} , ${add.street} , ${add.landmark} , ${add.district} ${add.pinCode} ${add.state}`
    }
})
.catch(()=>{
    window.alert("data not fetched")
})