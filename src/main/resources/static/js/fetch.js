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

    function sendPermissionRequest(userEmail, permissionType) {
            fetch('/viewCanvas', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userEmail: userEmail,
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