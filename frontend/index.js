
const URL = 'http://localhost:8080';
let allElectronics = [];
//the DOM will be loaded and call GET http://localhost:8080/warehouses  to get electronics JSON response
document.addEventListener('DOMContentLoaded', () => {
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
            let warehouses = JSON.parse(xhr.responseText);

            warehouses.forEach(warehouse => {
                let location = warehouse.location;
                warehouse.electronic.forEach(electronic => {
                    addElectronicToTable(location, electronic);
                });
            });
        }
    };

    xhr.open('GET', URL + '/warehouses');
    xhr.send();
});


//once the submit button called get all data in NewForm and pass it to post request
document.getElementById('new-electronic-form').addEventListener('submit', (event) => {
    event.preventDefault();
    let inputData = new FormData(document.getElementById('new-electronic-form'));
  
    let newElectronic= {
        type : inputData.get('new-electronic-type'),         
        model : inputData.get('new-electronic-model'),
        year : inputData.get('new-electronic-year'),         
        price : inputData.get('new-electronic-price'), 
        warehouseID : inputData.get('new-electronic-warehouseID'),        
        quantity : inputData.get('new-electronic-quantity')
    }
  
    doPostRequest(newElectronic);
  });
  
  async function doPostRequest(newElectronic) {
    try {
      let returnedData = await fetch(URL + '/electronics/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(newElectronic)
      });
  
      

        let electronicJson = await returnedData.json();

        document.getElementById('response-container-new').textContent = JSON.stringify(electronicJson.info);
           }
    catch (error) {
      console.error('Error:', error);
    }
  }
  
  
  

//creating the table by the data we got from DOM

function addElectronicToTable(location, electronic) {
    let tr = document.createElement('tr');
    let locationCell = document.createElement('td');
    let id = document.createElement('td');
    let type = document.createElement('td');
    let model = document.createElement('td');
    let year = document.createElement('td');
    let price = document.createElement('td');
    let warehouseID = document.createElement('td');
    let quantity = document.createElement('td');
    let editBtn = document.createElement('td');
    let deleteBtn = document.createElement('td');

    locationCell.innerText = location;
    id.innerText = electronic.id;
    type.innerText = electronic.type;
    model.innerText = electronic.model;
    year.innerText = electronic.year;
    price.innerText = electronic.price;
    quantity.innerText = electronic.quantity;
    warehouseID.innerText = electronic.warehouseID;

    editBtn.innerHTML = `<button class="btn btn-primary" id="edit-button" onclick="activateEditForm(${electronic.id})">Edit</button>`;
    deleteBtn.innerHTML = `<button class="btn btn-primary" id="delete-button" onclick="activateDeleteForm(${electronic.id})">Delete</button>`;

    
    tr.appendChild(id);
    tr.appendChild(type);
    tr.appendChild(model);
    tr.appendChild(year);
    tr.appendChild(price);
    tr.appendChild(locationCell);
    tr.appendChild(quantity);
    tr.appendChild(editBtn);
    tr.appendChild(deleteBtn);

    tr.setAttribute('id', 'TR' + electronic.id);

    document.getElementById('electronic-table-body').appendChild(tr);

    allElectronics.push(electronic);
}

document.getElementById('update-cancel-button').addEventListener('click', (event) => {
    event.preventDefault();
    resetAllForms();
});

document.getElementById('delete-cancel-button').addEventListener('click', (event) => {
    event.preventDefault();
    resetAllForms();
    
});
// clears all data from all forms
function resetAllForms() {


    document.getElementById('new-electronic-form').reset();
    document.getElementById('update-electronic-form').reset();
    document.getElementById('delete-electronic-form').reset();


    document.getElementById('new-electronic-form').style.display = 'block';
    document.getElementById('update-electronic-form').style.display = 'none';
    document.getElementById('delete-electronic-form').style.display = 'none'; 
}

function activateEditForm(electronicId) {

    for(let e of allElectronics) {
        if(e.id === electronicId) {
            document.getElementById('update-electronic-id').value = e.id;
            document.getElementById('update-electronic-type').value = e.type;
            document.getElementById('update-electronic-model').value = e.model;
            document.getElementById('update-electronic-year').value = e.year;
            document.getElementById('update-electronic-price').value = e.price;
            document.getElementById('update-electronic-warehouseID').value = e.warehouseID;
            document.getElementById('update-electronic-quantity').value = e.quantity;
        }
    }

    document.getElementById('update-electronic-form').style.display = 'block';   // block is the default for showing a tag
    document.getElementById('delete-electronic-form').style.display = 'none';

}

function activateDeleteForm(electronicId) {

    for(let e of allElectronics) {
        if(e.id === electronicId) {
            document.getElementById('delete-electronic-id').value = e.id;
            
            
        }
    }

  
    document.getElementById('update-electronic-form').style.display = 'none';
    document.getElementById('delete-electronic-form').style.display = 'block';   // block is the default for showing a tag
    
}







//once the submit button called get all data in delete form and pass it to post request

  document.getElementById('delete-electronic-form').addEventListener('submit', (event) => {
    event.preventDefault();		
    let electronicId = document.getElementById('delete-electronic-id').value;
    

    
     fetch(URL + '/electronics/delete/' + electronicId, {
        method : 'DELETE',
        headers: {
            "Content-Type": "application/json",
        }
    }).then((response) => response.json())
    .then((data) => {
        document.getElementById('response-container-delete').innerText = data.info;
       
    })
    .catch((error) => {
        console.error(error);   
    })
    
});
//once the submit button called get all data in update-form and pass it to post request
document.getElementById('update-electronic-form').addEventListener('submit', (event) => {

    event.preventDefault();         
  let inputData = new FormData(document.getElementById('update-electronic-form'));

  let updateElectronic = {
      id : document.getElementById('update-electronic-id').value, 
      type : inputData.get('update-electronic-type'),         
      model : inputData.get('update-electronic-model'),
      year : inputData.get('update-electronic-year'),         
      price : inputData.get('update-electronic-price'), 
      warehouseID : document.getElementById('update-electronic-warehouseID').value,        
      quantity : inputData.get('update-electronic-quantity')
      
  }


 
  doPostRequest(updateElectronic);

});

async function doPostRequest(updateElectronic) {

  let returnedData = await fetch(URL + '/electronics/add', {
      method : 'PUT',
      headers : {
          'Content-Type' : 'application/json'         
      },
      body : JSON.stringify(updateElectronic)      
  });

  let electronicJson = await returnedData.json();


  document.getElementById('response-container-update').innerText = JSON.stringify(electronicJson.info);


  
   document.getElementById('update-electronic-form').reset();
}