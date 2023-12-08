// to fetch the id from url we are using URLSearchParam
const urlParams = new URLSearchParams(window.location.search);
const workId = urlParams.get('workId');









let type=document.getElementById("type")

let name=document.getElementById("name")
let email=document.getElementById("email")
let phone=document.getElementById("phone")

let address=document.getElementById("fullAddress")

window.fetch(`http://localhost:8080/work?id=${workId}`,{
    method:"GET"
})
.then((x)=>x.json())
.then((obj)=>{
    console.log(obj);

    if(obj.status===302){
        type.textContent = obj.data.type
        name.textContent=obj.data.customer.name
        email.textContent= obj.data.customer.email
        phone.textContent=obj.data.customer.phone
        
        let add=obj.data.address
        address.textContent=`${add.d_no} , ${add.street} , ${add.landmark} , ${add.district} ${add.pinCode} ${add.state}`
    }
})
.catch(()=>{
    window.alert("data not fetched")
})



// the below code is belongs to start work

let button=document.getElementById("button")
console.log(button);
button.addEventListener("click",()=>{



    fetch(`http://localhost:8080/start?w_id=${workId}&v_id=${localStorage.getItem("id")}`,{
        method:"PUT"
       
     })
     .then((response)=>response.json())
     .then((obj)=>{
        console.log(obj);
        // console.log(obj.data);
        if(obj.data.startDate!=null){
           
            button.disabled = true;
            button.style.display = "none";
            window.alert(`you just started your work from the date ${obj.data.startDate}`)
        }
     })
     .catch((error)=>{
        console.error("the work not started yet")
     })
    
})





   