
const URL2 = 'http://localhost:8080';
let allWarehouses = [];

document.addEventListener('DOMContentLoaded', () => {
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
            let warehouses = JSON.parse(xhr.responseText);

            warehouses.forEach(newWarehouse => {

                    addWarehouseToTable(newWarehouse);
                });
            
        }
    };

    xhr.open('GET', URL2 + '/warehouses');
    xhr.send();
});

// ...

document.getElementById('new-warehouse-form').addEventListener('submit', (event) => {
    event.preventDefault();
    let inputData = new FormData(document.getElementById('new-warehouse-form'));
  
    let newWarehouse = {
      location: inputData.get('new-warehouse-location'),
      maxCapacity: inputData.get('new-warehouse-maxCapacity'),
    }
  
    doPostRequest(newWarehouse);
  });
  
  async function doPostRequest(newWarehouse) {
    try {
      let returnedData = await fetch(URL2 + '/warehouses/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(newWarehouse)
      });
  
      if (returnedData.ok) {
        let warehouseJson = await returnedData.json();
        document.getElementById('response-warehouse-container-new').textContent = JSON.stringify(warehouseJson.info);
      } else {
        console.error('Request failed with status:', returnedData.status);
      }
    } catch (error) {
      console.error('Error:', error);
    }
  }
  
  // ...
  

function addWarehouseToTable(newWarehouse) {
    let tr = document.createElement('tr');
    let locationCell = document.createElement('td');
    let id = document.createElement('td');
    let maxCapacity = document.createElement('td');
    let currentStock = document.createElement('td');
    let editBtn = document.createElement('td');
    let deleteBtn = document.createElement('td');
    

    locationCell.innerText = newWarehouse.location;
    id.innerText = newWarehouse.id;
    maxCapacity.innerText = newWarehouse.maxCapacity;
    currentStock.innerText = newWarehouse.currentStock;
    

    editBtn.innerHTML = `<button class="btn btn-primary" id="edit-button" onclick="activateEditForm(${newWarehouse.id})">Edit</button>`;
    // editBtn1.innerHTML = `<button class="btn btn-primary" id="edit-button" onclick="activateEditForm(${newWarehouse.id})">Edit</button>`;
   
    deleteBtn.innerHTML = `<button class="btn btn-primary" id="delete-button" onclick="activateDeleteForm(${newWarehouse.id})">Delete</button>`;

    tr.appendChild(id);
    tr.appendChild(locationCell);
    tr.appendChild(maxCapacity);
    tr.appendChild(currentStock);
    tr.appendChild(editBtn);
    tr.appendChild(deleteBtn);

    tr.setAttribute('id', 'TR' + id);

    document.getElementById('warehouse-table-body').appendChild(tr);

    allWarehouses.push(newWarehouse);
}

document.getElementById('update-cancel-button').addEventListener('click', (event) => {
    event.preventDefault();
    resetAllForms();
});

document.getElementById('delete-cancel-button').addEventListener('click', (event) => {
    event.preventDefault();
    resetAllForms();
    
});

function resetAllForms() {

    // clears data from all forms
    document.getElementById('new-warehouse-form').reset();
    document.getElementById('update-warehouse-form').reset();
    document.getElementById('delete-warehouse-form').reset();

    // dispalys only the new-movie-form
    document.getElementById('new-warehouse-form').style.display = 'block';
    document.getElementById('update-warehouse-form').style.display = 'none';
    document.getElementById('delete-warehouse-form').style.display = 'none'; 
}

function activateEditForm(warehouseId) {
    // find the movie and its <tr> that needs to be edited
    for(let w of allWarehouses) {
        if(w.id === warehouseId) {
            document.getElementById('update-warehouse-id').value = w.id;
            document.getElementById('update-warehouse-location').value = w.location;
            document.getElementById('update-warehouse-maxCapacity').value = w.maxCapacity;
            document.getElementById('update-warehouse-currentStock').value = w.currentStock;
            
        }
    }

    // showing only the edit form
    document.getElementById('new-warehouse-form').style.display = 'none';
    document.getElementById('update-warehouse-form').style.display = 'block';   // block is the default for showing a tag
    document.getElementById('delete-warehouse-form').style.display = 'none';

}

function activateDeleteForm(warehouseId) {
    for(let w of allWarehouses) {
        if(w.id === warehouseId) {
            document.getElementById('delete-warehouse-id').value = w.id;
            
        }
    }

  
    document.getElementById('new-warehouse-form').style.display = 'none';
    
    document.getElementById('delete-warehouse-form').style.display = 'block';   
    
}









document.getElementById('delete-warehouse-form').addEventListener('submit', (event) => {
    event.preventDefault();		
    let warehouseId = document.getElementById('delete-warehouse-id').value;
    

    
    fetch(URL2 + '/warehouses/delete/' + warehouseId, {
        method : 'DELETE',
        headers: {
            "Content-Type": "application/json",
        }
    }).then((response) => response.json())
    .then((data) => {
        document.getElementById('response-warehouse-container-delete').innerText = data.info;
    })
    .catch((error) => {
        console.error(error);   
    })

});

document.getElementById('update-warehouse-form').addEventListener('submit', (event) => {

    event.preventDefault();         
  let inputData = new FormData(document.getElementById('update-warehouse-form'));

  let updateWarehouse = {
      id : document.getElementById('update-warehouse-id').value, 
      currentStock : document.getElementById('update-warehouse-currentStock').value,
      location : inputData.get('update-warehouse-location'),         
      maxCapacity : inputData.get('update-warehouse-maxCapacity')
      
     
      
  }


 
  doPostRequest(updateWarehouse);

});

async function doPostRequest(updateWarehouse) {

  let returnedData = await fetch(URL2 + '/warehouses/add', {
      method : 'POST',
      headers : {
          'Content-Type' : 'application/json'         
      },
      body : JSON.stringify(updateWarehouse)      
  });

  let warehouseJson = await returnedData.json();

document.getElementById('response-warehouse-container-update').innerText = JSON.stringify(warehouseJson.info);
//   console.log(
//        document.getElementById('response-container').innerText = JSON.stringify(warehouseJson.info)
//       )
//    document.getElementById('update-warehouse-form').reset();
}
