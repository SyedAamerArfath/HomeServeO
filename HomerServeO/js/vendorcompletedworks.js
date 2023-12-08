



const workTableContainer = document.getElementById('table-container');

    fetch(`http://localhost:8080/work/completedworks?v_id=${localStorage.getItem("id")}`)
        .then(response => response.json())
        .then(data => {
            
            console.log(data);
            const workTableBody = document.getElementById('workdata');
           
            // to clear the previus data else it will just add data without clearing previous leads to duplicate datas
            workTableBody.innerHTML=""

                for(let i=0;i<=data.data.length-1;i++){
                    let item=data.data[i]
                    const type=data.data[i].type
                    const startDate=data.data[i].startDate
                    const endDate=data.data[i].endDate
                    const  vname=item.customer.name
                    const vemail=item.customer.email
                    const vphone=item.customer.phone
                    const loc=data.data[i].address
                    const totalAmount = item.cost ? item.cost.totalAmount : 0; // Initialize totalAmount to 0 if cost is null
              
                   
                   
                const row = workTableBody.insertRow();
                row.innerHTML = `
                    <td>${type}</td>
                    <td>${startDate}</td>
                    <td>${endDate}</td>
                    <td>${loc.d_no} ${loc.street} ${loc.landmark} ${loc.district} ${loc.pinCode} ${loc.state}</td>
                    <td>${vname}</td>
                    <td>${vemail}</td>
                    <td>${vphone}</td>
                    <td><button id="button" class="hidden" >Amount</button></td>
                    <td>${totalAmount}</td>
                `;
                    
                    
                          // the below code belongs to GenerateCost
                    const generateCost= row.querySelector(".hidden");
 
            generateCost.addEventListener("click",(e)=>{



    fetch(`http://localhost:8080/cost?w_id=${item.id}&v_id=${localStorage.getItem("id")}`,{
        method:"POST"
    })
    .then((response)=>response.json())
    .then((obj)=>{
        console.log(obj)
        if(obj.data!=null){
           
        window.alert(`you have completed your work :
       no Of days:${obj.data.days}
       totalAmount:${obj.data.totalAmount} `)
        }
    })
    .catch((error)=>{
        console.error("error generating service cost")
    })

})


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
