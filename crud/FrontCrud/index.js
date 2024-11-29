const employeeTableHTML = document.getElementById('tablaProductos')
const nameBox =  document.getElementById('name')
const address =  document.getElementById('address')
const email =  document.getElementById('email')
const phoneNumber = document.getElementById('phoneNumber')
const saveBtn =  document.getElementById('btnGuardar')
let modalTitle = document.getElementById('modalTitle')
let idProductoModificar = 0

async function reloadTable() {
    employeeTableHTML.innerHTML = '';
    setTimeout(() => {
        getEmployees();
    }, 10);
}

saveBtn.addEventListener("click",()=>{
    if(modalTitle.innerText==="Agregar Empleado"){
        saveEmployee();
    }else{
        updateEmployee();
    }
});

async function saveEmployee(){
    let employee = {
        name:nameBox.value,
        address:address.value,
        email:email.value,
        phoneNumber:phoneNumber.value
    }
    fetch('http://localhost:8080/api/v1/employees', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(employee)
    })
    reloadTable();
}

async function panUpdateEmployee(id){
    modalTitle.innerText = "Modificar Producto";
    modal.style.display = "block";
    idProductoModificar = id;
    let employee = employeesList.find(employee => employee.id === id)
    resetModal()
    nameBox.value = employee.name
    address.value = employee.address
    email.value = employee.email
    phoneNumber.value = employee.phoneNumber
    
}

async function updateEmployee(){
    let employee = {
        id:idProductoModificar,
        name:nameBox.value,
        address:address.value,
        email:email.value,
        phoneNumber:phoneNumber.value
    }
    fetch('http://localhost:8080/api/v1/employees', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(employee)
    })
    reloadTable();
}

async function deleteEmployee(id){
    fetch('http://localhost:8080/api/v1/employees/'+id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    reloadTable();
}

let employeesList = []
async function getEmployees(){
    fetch('http://localhost:8080/api/v1/employees')
      .then(response => response.json())
      .then(employees => {
        employeesList = employees;
        for(let employee of employees){
            employeeTableHTML.innerHTML +=
            '<tr>'+
            '    <th scope="row">'+employee.id+'</th>'+
            '    <td>'+employee.name+'</td>'+
            '    <td>'+employee.email+'</td>'+
            '    <td>'+employee.address+'</td>'+
            '    <td>'+employee.phoneNumber+'</td>'+
            '    <td>'+
            '        <input type="button" class="btn btn-outline-danger" value="Eliminar" onclick=deleteEmployee('+employee.id+')>'+
            '        <input type="button" id="myBtn" class="btn btn-primary" value="Modificar" onclick=panUpdateEmployee('+employee.id+')>'+
            '    </td>'+
            '</tr>'
        }
    });
}
getEmployees();


//Reset Modal
function resetModal(){
    nameBox.value = ""
    email.value = ""
    address.value = ""
    phoneNumber.value =""
}


//Interfaz modificar employee 
var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn");
var span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
    modalTitle.innerText = "Agregar Empleado"
    modal.style.display = "block";
}

span.onclick = function() {
  modal.style.display = "none";
  resetModal()
}

window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
    resetModal()
  }
}