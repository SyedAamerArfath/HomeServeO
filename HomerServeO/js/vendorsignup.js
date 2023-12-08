const form = document.getElementById('form');
const name = document.getElementById('name');
const phone = document.getElementById('phone');
const email = document.getElementById('email');
const password = document.getElementById('password');
const role = document.getElementById('role')
const costperday = document.getElementById('costperday');
const DoorNumber = document.getElementById('DoorNumber');
const Street = document.getElementById('Street');
const Landmark = document.getElementById('Landmark');
const District = document.getElementById('District');
const pincode = document.getElementById('pincode');
const State = document.getElementById('State');



form.addEventListener('submit', e => {
    e.preventDefault();

    if(name.value===''&&email.value===''&& phone.value===''&&password.value===''&&role.value===''&&costperday.value===''&&DoorNumber.value===''
    &&Street.value===''&&Landmark.value===''&&District.value===''&&pincode.value===''&&State.value===''){
       
            checkInputs();
        alert("all fields are mandatory")
    
        
        
        return false
    }else{






    checkInputs();

    // console.log(name.value);
    // console.log(phone.value);
    // console.log(email.value);
    // console.log(password.value);
    
    // console.log(costperday.value);
    // console.log(DoorNumber.value);
    // console.log(Landmark.value);
    // console.log(Street.value);
    // console.log(District.value);
    // console.log(State.value);
    // console.log(pincode.value);

    var obj={
        name:name.value,
        email:email.value,
        phone:phone.value,
        pwd:password.value,
        costPerDay:costperday.value,
        role:role.value,
        address:{
           d_no:DoorNumber.value,
           street:Street.value,
           landmark:Landmark.value,
           district:District.value,
           state:State.value,
           pinCode:pincode.value
        }
        
     }
     fetch('http://localhost:8080/vendor', {
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
            window.location.href="http://127.0.0.1:5500/vendorlogin.html"
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
    const roleValue = role.value;
    const costperdayValue = costperday.value.trim();
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
        setErrorFor(password, 'atleast one number, one uppercase, lowercase lette, and atleast 8 character');
    }else {
        setSuccessFor(password);
    }

   
    if(role.value === ""){
        setErrorFor(role , "please enter role")
    }else{
        setSuccessFor(role)
    }



   


    if (costperdayValue === '') {
        setErrorFor(costperday, 'Please enter your costperday');
    } else {
        setSuccessFor(costperday);
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






