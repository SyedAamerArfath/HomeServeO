const form = document.getElementById('form');
const name = document.getElementById('name');
const phone = document.getElementById('phone');
const email = document.getElementById('email');
const password = document.getElementById('password');
const count = document.getElementById('count');
let DoorNumber = document.getElementById('DoorNumber');
let Street = document.getElementById('Street');
let Landmark = document.getElementById('Landmark');
let District = document.getElementById('District');
let pincode = document.getElementById('pincode');
let State = document.getElementById('State');


form.addEventListener('submit', e => {
    e.preventDefault();


    if(name.value===''&&email.value===''&& phone.value===''&&password.value===''&&count.value===''&&DoorNumber.value===''
    &&Street.value===''&&Landmark.value===''&&District.value===''&&pincode.value===''&&State.value===''){
       
            checkInputs();
        alert("all fields are mandatory")
    
        
        
        return false
    }else
{


    if(DoorNumber.value===""){
        DoorNumber=localStorage.getItem("door number")
    }
    if(Street.value===""){
        Street=localStorage.getItem('street')
    }
    if(Landmark.value===""){
        Landmark=localStorage.getItem("landmark")
    }
    if(District.value===""){
        District=localStorage.getItem("district")
    }
    if(pincode.value===""){
        pincode=localStorage.getItem("pin-code")
    }
    if(State.value===""){
        State=localStorage.getItem("state")
    }




    checkInputs();
    // console.log(name.value);
    // console.log(phone.value);
    // console.log(email.value);
    // console.log(password.value);

    // console.log(DoorNumber.value);
    // console.log(Landmark.value);
    // console.log(Street.value);
    // console.log(District.value);
    // console.log(State.value);
    // console.log(pincode.value);

    var obj={
        id:localStorage.getItem("id"),
        name:name.value,
        email:email.value,
        phone:phone.value,
        pwd:password.value,
        familyCount:count.value,

        address:{
            id:localStorage.getItem("address Id"),
           d_no:DoorNumber.value,
           street:Street.value,
           landmark:Landmark.value,
           district:District.value,
           state:State.value,
           pinCode:pincode.value
        }
        
     }
     fetch('http://localhost:8080/customer', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(obj)
      })
        .then(response => response.json())
        .then(obj => {
          
          console.log(obj);
          if(obj.status===302){

            localStorage.setItem("id",obj.data.id)
            localStorage.setItem("name",obj.data.name)
            localStorage.setItem("email",obj.data.email)
            localStorage.setItem("phone",obj.data.phone)
            localStorage.setItem("password",obj.data.pwd)
            localStorage.setItem("familyCount",obj.data.familyCount)
            localStorage.setItem("address Id" ,obj.data.address.id)
             localStorage.setItem("door number",obj.data.address.d_no)
             localStorage.setItem("street",obj.data.address.street)
             localStorage.setItem("landmark",obj.data.address.landmark)
             localStorage.setItem("district",obj.data.address.district)
             localStorage.setItem("state",obj.data.address.state)
             localStorage.setItem("pin-code",obj.data.address.pinCode)
            window.location.href="http://127.0.0.1:5500/customerpage.html"
          }
        })
        .catch(error => {
        
          console.error("error")
        });
}

});

function checkInputs() {
    // trim to remove the whitespaces
    const nameValue = name.value.trim();
    const phoneValue = phone.value.trim();
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
    const countValue=count.value
    const DoorNumberValue = DoorNumber.value.trim();
    const StreetValue = Street.value.trim();
    const LandmarkValue = Landmark.value.trim();
    const DistrictValue = District.value.trim();
    const pincodeValue = pincode.value.trim();
    const StateValue = State.value.trim();


    if (nameValue === '') {
    
        setErrorFor(name, 'Please enter your name');
    } else {
        setSuccessFor(name);
    }
    if (phoneValue === '') {
        setErrorFor(phone, 'Please enter phoneNumber');
     } else if (!isPhone(phoneValue)) {
            setErrorFor(phone, 'phonenumber is not valid');
    } else {
        setSuccessFor(phone);
    }

    if (emailValue === '') {
        setErrorFor(email, 'Please enter your email');
    } else if (!isEmail(emailValue)) {
        setErrorFor(email, 'Email not valid');
    } else {
        setSuccessFor(email);
    }

    if (passwordValue === '') {
        setErrorFor(password, 'Please enter password');
    } else if (!isPassword(passwordValue)) {
        setErrorFor(password, 'atleast one number, one uppercase, lowercase letter,one special character, and atleast 8 character');
    }else {
        setSuccessFor(password);
    }



    if (count.value === '') {
        setErrorFor(count, 'Please enter your familyCount');
    } else {
        setSuccessFor(count);
    }

    if (DoorNumberValue === '') {
        setErrorFor(DoorNumber, 'Please enter your DoorNumber');
    } else {
        setSuccessFor(DoorNumber);
    }

    if (StreetValue === '') {
        setErrorFor(Street, 'Please enter your Street');
    } else {
        setSuccessFor(Street);
    }

    if (LandmarkValue === '') {
        setErrorFor(Landmark, 'Please enter your Landmark');
    } else {
        setSuccessFor(Landmark);
    }

    if (DistrictValue === '') {
        setErrorFor(District, 'Please enter your District');
    } else {
        setSuccessFor(District);
    }

    if (pincodeValue === '') {
        setErrorFor(pincode, 'Please enter your pincode');
    } else {
        setSuccessFor(pincode);
    }

    if (StateValue === '') {
        setErrorFor(State, 'Please enter your State');
    } else {
        setSuccessFor(State);
    }

    
}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector("small");
    formControl.className = 'form-control error';
    small.innerText = message;
}

function setSuccessFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'form-control success';
}
function isPhone(phone){
    return /^([+]\d{2})\d{10}/.test(phone)
}

function isEmail(email) {
    return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}

function isPassword(password){  
    return /^(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[a-zA-Z!#$%&? "])[a-zA-Z0-9!#$%&?]{8,12}$/.test(password);
}




