 /*function sendReadRequest(id, name) {
        fetch(`/viewCanvas/read?id=${id}&user_email=${name}`, {
            method: 'POST',
            credentials: 'same-origin'
            // Puedes añadir más opciones como headers, body, etc., según sea necesario
        }).then(response => {
            // Manejar la respuesta del servidor si es necesario
            console.log('Solicitud de lectura enviada');
        }).catch(error => {
            // Manejar errores si la solicitud falla
            console.error('Error al enviar solicitud de lectura:', error);
        });
    }

    function sendWriteRequest(id, name) {
        fetch(`/viewCanvas/write?id=${id}&nameCanvas=${name}`, {
            method: 'POST',
            credentials: 'same-origin'
            // Puedes añadir más opciones como headers, body, etc., según sea necesario
        }).then(response => {
            // Manejar la respuesta del servidor si es necesario
            console.log('Solicitud de escritura enviada');
        }).catch(error => {
            // Manejar errores si la solicitud falla
            console.error('Error al enviar solicitud de escritura:', error);
        });
    }
    */

    /*
    function sendPermissionRequest(userEmail, permissionType) {
        console.log(userEmail);
        fetch('/viewCanvas', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    user_email: userEmail,
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
        */


/*
function confirmDeleteCanvas(canvasId) {
        if (confirm("¿Estás seguro de que quieres borrar el canvas?")) {
            deleteCanvas(canvasId);
        } else {
            // El usuario canceló la eliminación
            console.log("Eliminación cancelada.");
        }
    }

    function deleteCanvas(canvasId) {
        fetch('/trash/delete', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id: canvasId })
        })
        .then(response => {
            if (response.ok) {
                console.log("Canvas eliminado correctamente.");
                // Realiza aquí cualquier otra acción después de eliminar el canvas
            } else {
                console.error("Error al eliminar el canvas.");
                // Maneja el caso de error al eliminar el canvas
            }
        })
        .catch(error => {
            console.error("Error al eliminar el canvas:", error);
            // Maneja errores de red u otros errores
        });
    }
    */
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
            // Capturar el evento de cambio del select
            document.addEventListener("DOMContentLoaded", function() {
                var selectElement = document.querySelector('select');
            
                selectElement.addEventListener('change', function() {
                    var selectedVersion = this.value; // Obtener la versión seleccionada
            
                    // Realizar una solicitud FETCH al servidor
                    fetch('/viewCanvas?id=' + selectedVersion) // Ajustar la URL según tu configuración
                        .then(function(response) {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                            return response.json();
                        })
                        .then(function(data) {
                            // Actualizar los valores de los atributos con los nuevos datos
                            document.getElementById('llistaFigureJson').value = data.llistaFigureJson;
                            document.getElementById('llistaStroke').value = data.llistaStroke;
                            // Puedes realizar otras acciones con los datos si es necesario
                        })
                        .catch(function(error) {
                            console.error('Error al obtener los datos de la versión seleccionada:', error);
                        });
                });
            });

*/

