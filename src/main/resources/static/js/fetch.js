
function confirmDelete() {
    return confirm("¿Estás seguro de que quieres borrar el canvas definitivamente ? ");
}

function sendPermissionRequest(permissionType, button) {
    var email = button.parentNode.parentNode.parentNode.firstElementChild.innerText; // Obtiene el correo electrónico de la primera columna
    console.log('Email:', email, 'Permission:', permissionType);
    fetch('/viewCanvas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            user_email: email,
            permissionType: permissionType
        })

    })
        .then(response => {
            // Manejar la respuesta del servidor si es necesario
            console.log('Solicitud de permiso enviada');
        })
        .catch(error => {
            // Manejar errores si la solicitud falla
            console.error('Error al enviar solicitud de permiso:', error);
        });
}

function sendCopyRequest(button) {
    // Obtener el valor seleccionado en el <select>
    var selectedVersionId = document.querySelector('select').value;

    // Hacer la solicitud con fetch
    fetch('/viewCanvas/copy', {
        method: 'POST', // O el método que corresponda
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            idVersion: selectedVersionId

        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al copiar la versión');
            }
            // Hacer algo con la respuesta si es necesaria
            return response.json();
        })
        .then(data => {
            // Manejar la respuesta si es necesaria
            console.log('Versión copiada con éxito', data);
        })
        .catch(error => {
            console.error('Hubo un problema al copiar la versión:', error);
        });
}

/*
// Obtener el elemento select por su id
let select = document.querySelector("select");

// Añadir un evento change al select
select.addEventListener("change", function () {
    
    // Obtener el valor del select
    let idVersion = select.value;
    // Crear un objeto con el idVersion como propiedad
    let data = { idVersion: idVersion };

    // Crear una opción de configuración para el fetch
    let options = {
        method: "POST", // Método de envío
        headers: {
            "Content-Type": "application/json" // Tipo de contenido
        },
        body: JSON.stringify(data) // Convertir el objeto a JSON
    };

    // Enviar el fetch al controlador de java
    fetch("/viewCanvas/version", options)
        .then(response => response.json()) // Obtener la respuesta como JSON
        .then(data => {
            // Hacer algo con los datos recibidos
            console.log(data);
        })
        .catch(error => {
            // Manejar el error
            console.error(error);
            console.log("Ha fallat")
        });
});
*/