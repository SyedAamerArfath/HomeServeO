// to fetch the id from url we are using URLSearchParam
const urlParams = new URLSearchParams(window.location.search);
const vendorId = urlParams.get('vendorId');


const workHeading = document.getElementById('table-container');


    fetch(`http://localhost:8080/work/completedworks?v_id=${vendorId}`)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            const workData = document.getElementById('workdata');

            // Clear existing rows
            workData.innerHTML = "";

            for (let i = 0; i < data.data.length; i++) {
                const item = data.data[i];
                const type = item.type;
                const startDate = item.startDate;
                const endDate = item.endDate;
                const loc = item.address;
                const totalAmount = item.cost ? item.cost.totalAmount : 0; // Initialize totalAmount to 0 if cost is null



                // Iterate through vendors for each work item
                for (let j = 0; j < item.vendor.length; j++) {
                    const vname = item.vendor[j].name;
                    const vemail = item.vendor[j].email;
                    const vphone = item.vendor[j].phone;

                  
                    const row = workData.insertRow();
                    row.innerHTML = `
                        <td>${type}</td>
                        <td>${startDate}</td>
                        <td>${endDate}</td>
                        <td>${loc.d_no} ${loc.street} ${loc.landmark} ${loc.district} ${loc.pinCode} ${loc.state}</td>
                        <td>${vname}</td>
                        <td>${vemail}</td>
                        <td>${vphone}</td>
                        <td>${totalAmount}</td>
                        <td><ul><li class="dropdown">
                        <button class="button" id="payment" >Payment</button>
                         <ul class="dropdown-content">
                           <li><a href="#" data-option="cash"  id="cash">Cash</a></li>
                           <li><a href="#" data-option="Gpay"  id="gpay">GPay</a></li>
                           <li><a href="#" data-option="Phonepe"  id="phonepay">PhonePe</a></li>
                           <li><a href="#" data-option="Amazonpay"  id="amazonpay">AmazonPay</a></li>
                           <li><a href="#" data-option="Credit Card"  id="creditcard">Credit Card</a></li>
                           <li><a href="#" data-option="Paypal"  id="paypal">PayPal</a></li>
                         </ul>
                       </li></ul></td>
                       
                       
                    `;
               
             



   // the below code belongs to payment option

// Event delegation for payment options

row.querySelector('.dropdown-content').addEventListener('click', function (event) {
  // Check if the clicked element is a payment option link
  if (event.target.matches('.dropdown-content a')) {
    event.preventDefault();

   

    let mode = event.target.textContent;
    
    console.log(mode);
    var obj = {
      id: item.cost.id,
      mode: mode
    }

    fetch(`http://localhost:8080/cost?c_id=${localStorage.getItem("id")}`, {
      method: "PUT",
      headers: {
        'Content-Type': 'Application/Json'
      },
      body: JSON.stringify(obj)
    })
      // .then((x) => x.json())
      .then((obj) => {
        console.log(obj);
      })
      .catch((error) => {
        console.log("mode not set");
      });

    // Hide the dropdown
    event.target.closest('.dropdown-content').style.display = 'none';
  }
});

// Event listener to toggle the dropdown visibility
document.getElementById('payment').addEventListener('click', function (event) {
  event.preventDefault();
  const paymentDropdown = this.nextElementSibling; // Get the sibling ul.dropdown-content
  paymentDropdown.style.display = (paymentDropdown.style.display === 'block') ? 'none' : 'block';
});




}
}

            
})
.catch(error => {
    console.error('Error fetching work data:', error);
});      
