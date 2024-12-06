const employeeTableHTML = document.getElementById('tablaProductos');
const nameBox = document.getElementById('name');
const address = document.getElementById('address');
const email = document.getElementById('email');
const phoneNumber = document.getElementById('phoneNumber');
const saveBtn = document.getElementById('btnGuardar');
const selectAllCheckbox = document.getElementById('selectAll');
const deleteSelectedButton = document.getElementById('deleteSelected');
const validationErrorModal = document.getElementById('validationErrorModal');
let modalTitle = document.getElementById('modalTitle');
let idProductoModificar = 0;

async function reloadTable() {
    employeeTableHTML.innerHTML = '';
    await getEmployees();
}

saveBtn.addEventListener("click", () => {
    if (modalTitle.innerText === "Agregar Empleado") {
        saveEmployee();
    } else {
        updateEmployee();
    }
});

selectAllCheckbox.addEventListener('change', function () {
    const checkboxes = employeeTableHTML.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(checkbox => {
        checkbox.checked = selectAllCheckbox.checked;
    });
});

deleteSelectedButton.addEventListener('click', async function () {
    const selectedCheckboxes = employeeTableHTML.querySelectorAll('input[type="checkbox"]:checked');
    const idsToDelete = Array.from(selectedCheckboxes).map(checkbox => checkbox.dataset.id);

    for (const id of idsToDelete) {
        await deleteEmployee(id);
    }
    await reloadTable();
    selectAllCheckbox.checked = false;
});

async function saveEmployee() {
    if (!nameBox.value || !address.value || !email.value || !phoneNumber.value) {
        validationErrorModal.style.zIndex = "2";
        validationErrorModal.style.display = "block";
        return;
    }

    let employee = {
        name: nameBox.value,
        address: address.value,
        email: email.value,
        phoneNumber: parseInt(phoneNumber.value)
    };
    console.log(typeof employee.phoneNumber);
    console.log(employee.phoneNumber)
    await fetch('http://localhost:8080/api/v1/employees', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(employee)
    });
    await reloadTable();
    resetModal();
}

async function panUpdateEmployee(id) {
    modalTitle.innerText = "Modificar Producto";
    modal.style.display = "block";
    idProductoModificar = id;
    let employee = employeesList.find(employee => employee.id === id);
    resetModal();
    nameBox.value = employee.name;
    address.value = employee.address;
    email.value = employee.email;
    phoneNumber.value = parseInt(employee.phoneNumber);
}

async function updateEmployee() {
    let employee = {
        id: idProductoModificar,
        name: nameBox.value,
        address: address.value,
        email: email.value,
        phoneNumber: phoneNumber.value
    };
    await fetch('http://localhost:8080/api/v1/employees', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(employee)
    });
    await reloadTable();
}

async function deleteEmployee(id) {
    await fetch('http://localhost:8080/api/v1/employees/' + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    await reloadTable();
}

let employeesList = [];
async function getEmployees() {
    const response = await fetch('http://localhost:8080/api/v1/employees');
    const employees = await response.json();
    employeesList = employees;
    for (let employee of employees) {
        employeeTableHTML.innerHTML +=
            '<tr>' +
            '    <td><input type="checkbox" data-id="' + employee.id + '"></td>' +
            '    <th scope="row">' + employee.id + '</th>' +
            '    <td>' + employee.name + '</td>' +
            '    <td>' + employee.email + '</td>' +
            '    <td>' + employee.address + '</td>' +
            '    <td>' + employee.phoneNumber + '</td>' +
            '    <td>' +
            '        <input type="button" class="btn btn-outline-danger" value="Eliminar" onclick="deleteEmployee(' + employee.id + ')">' +
            '        <input type="button" id="myBtn" class="btn btn-primary" value="Modificar" onclick="panUpdateEmployee(' + employee.id + ')">' +
            '    </td>' +
            '</tr>';
    }
}
getEmployees();

function resetModal() {
    nameBox.value = "";
    email.value = "";
    address.value = "";
    phoneNumber.value = "";
}

var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn");
var span = document.getElementsByClassName("close")[0];
var close_alert = document.getElementsByClassName("close-alert")[0];

btn.onclick = function () {
    modalTitle.innerText = "Agregar Empleado";
    modal.style.display = "block";
}

span.onclick = function () {
    modal.style.display = "none";
    resetModal();
}

close_alert.onclick = function () {
    validationErrorModal.style.display = "none";
}

window.onclick = function (event) {
    if (event.target === validationErrorModal) {
        validationErrorModal.style.display = "none";
    } else if (event.target === modal) {
        modal.style.display = "none";
        resetModal();
    }
}