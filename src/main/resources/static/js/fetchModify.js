const saveButton = document.createElement('guardarDibuix');
// Event listener para el botón de guardar
saveButton.addEventListener("click", () => {
    console.log("Save button")
    saveFigures();
});

// Función para guardar figuras
async function saveFigures() {
    try {
        // Convertimos el array "figures" a una cadena JSON
        var figuresJSON = JSON.stringify(figures);

        const formData = new FormData();
        // Adjuntar las figuras en formato JSON
        formData.append("figuresData", figuresJSON);
        formData.append("strokesData", strokesJSON);


        // Adjuntar la visibilidad
        formData.append("toggleVisibility", visibility);

        // Obtener el valor del nombre
        const nameCanvas = document.getElementById("nomDibuix").value;
        formData.append("NomImage", imageName);

        // Enviar las figuras y la imagen al servidor
        const response = await fetch('/modify', {
            method: 'POST',
            body: formData,
        });
       // Manejar la respuesta del servidor
        if (response.ok) {
            showMessage('Operación exitosa', true);
        } else {
            showMessage('Error en la solicitud: ' + response.statusText, false);
        }

    } catch (error) {
            showMessage('Error al realizar la solicitud: ' + error.message, false);
    }
}
