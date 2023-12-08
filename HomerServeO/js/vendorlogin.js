const form = document.querySelector("form");
eField = form.querySelector(".email"),
eInput = eField.querySelector("input"),
pField = form.querySelector(".password"),
pInput = pField.querySelector("input");


form.onsubmit = (e)=>{
  e.preventDefault(); 
  (eInput.value == "") ? eField.classList.add("shake", "error") : checkEmail();
  (pInput.value == "") ? pField.classList.add("shake", "error") : checkPass();
//   console.log(eInput.value);
//   console.log(pInput.value);

  setTimeout(()=>{ 
    eField.classList.remove("shake");
    pField.classList.remove("shake");
  }, 500);

  eInput.onkeyup = ()=>{checkEmail();} 
  pInput.onkeyup = ()=>{checkPass();} 

  function checkEmail(){ 
    let pattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/; 
    if(!eInput.value.match(pattern)){ 
      eField.classList.add("error");
      eField.classList.remove("valid");
      let errorTxt = eField.querySelector(".error-txt");
      
      (eInput.value != "") ? errorTxt.innerText = "Enter a valid email address" : errorTxt.innerText = "Email can't be blank";
    }else{ 
      eField.classList.remove("error");
      eField.classList.add("valid");
    }
  }

  function checkPass(){ 
    let pattern = /^(?=.\d)(?=.[a-z])(?=.*[A-Z]).{8,12}$/;
    if(!pInput.value===""){ 
      pField.classList.add("error");
      pField.classList.remove("valid");
      let errorTxt = pField.querySelector(".error-txt");
      
      // (pInput.value != "") ? errorTxt.innerText = "must contain 8 characters, atleast one number, one uppercase letter and one lowercase letter" : errorTxt.innerText = "Password can't be blank";
    }else{ 
      pField.classList.remove('error');
      pField.classList.add("valid");
    }
  }
  if(!eField.classList.contains("error") && !pField.classList.contains("error")){
    window.location.href = form.getAttribute("action"); 
  }


// integration code starts from here

  let email=document.getElementById('email').value
let password=document.getElementById('password').value

if(email===""&&password===""){
  alert("email and password cannot be empty")
  return false
}
else{


  
fetch(`http://localhost:8080/vendor/login?email=${email}&pwd=${password}`, {
        method: 'Get'
        
      })
        .then(response => response.json())
       
        .then(obj => {
          localStorage.setItem("id",obj.data.id)
        localStorage.setItem("name",obj.data.name)
        localStorage.setItem("email",obj.data.email)
        localStorage.setItem("phone",obj.data.phone)
        localStorage.setItem("password",obj.data.pwd)
        localStorage.setItem("costperday",obj.data.costPerDay)
        localStorage.setItem("role",obj.data.role)
        localStorage.setItem("address Id" ,obj.data.address.id)
         localStorage.setItem("door number",obj.data.address.d_no)
         localStorage.setItem("street",obj.data.address.street)
         localStorage.setItem("landmark",obj.data.address.landmark)
         localStorage.setItem("district",obj.data.address.district)
         localStorage.setItem("state",obj.data.address.state)
         localStorage.setItem("pin-code",obj.data.address.pinCode)
          
          if(obj.data!=null){
            window.location.href="http://127.0.0.1:5500/vendorpage.html"
          }

        })
        .catch(error => {
          
          alert("invalid email or password")
          console.error("invalid email or password")
        });
         return true
      }
}


  
