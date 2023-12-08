
// to fetch the id from url we are using URLSearchParam
const urlParams = new URLSearchParams(window.location.search);
const vendorId = urlParams.get('vendorId');


const workTableContainer = document.getElementById('table-container');

    fetch(`http://localhost:8080/work/ongoingworks?v_id=${vendorId}`)
        .then(response => response.json())
        .then(data => {
            
            console.log(data);
            const workTableBody = document.getElementById('workdata');
           
            // to clear the previus data else it will just add data without clearing previous leads to duplicate datas
            workTableBody.innerHTML=""

                for(let i=0;i<=data.data.length-1;i++){
                    let item=data.data[i]
                    const type=item.type
                    const startDate=item.startDate
                    const endDate=item.endDate
                    
                    const loc=item.address

                    // in the response vendor is list of object so to iterate it we require another for loop
                    for(let j=0;j<=item.vendor.length-1;j++){
                    const  vname=item.vendor[j].name
                    const vemail=item.vendor[j].email
                    const vphone=item.vendor[j].phone
                
                   

                const row = workTableBody.insertRow();
                row.innerHTML = `
                    <td>${type}</td>
                    <td>${startDate}</td>
                    <td>${endDate}</td>
                    <td>${loc.d_no} ${loc.street} ${loc.landmark} ${loc.district} ${loc.pinCode} ${loc.state}</td>
                    <td>${vname}</td>
                    <td>${vemail}</td>
                    <td>${vphone}</td>
                `;
                    }
                }
            

            let display=  workTableContainer.classList.toggle("true")
              if(display){
                    workTableContainer.style.display="block"
              }
             else{
             workTableContainer.style.display="none"
                 }
        })
        .catch(error => {
            console.error('Error fetching work data:', error);
        });
