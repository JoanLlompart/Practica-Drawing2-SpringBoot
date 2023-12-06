/*const saveButton = document.createElement('guardarDibuix');
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
*/
// Obtener los datos que deseas enviar al servidor
const figuresData = document.getElementById('llistaFigureJson').value;
const strokesData = document.getElementById('llistaStroke').value;

// Datos adicionales que quieras enviar
const nombreCanvas = document.getElementById('nomDibuix').value;
const isPublico = document.getElementById('toggleVisibility').checked;

// Crear el objeto con los datos a enviar
const data = {
    strokesData: strokesData,
    figuresData: figuresData,
    nomDibuix: nameCanvas,
    isPublic: isPublic
};

// Realizar la solicitud POST al servidor
fetch('/modify', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json' // Especificar el tipo de contenido como JSON
    },
    body: JSON.stringify(data) // Convertir el objeto a JSON
})
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al enviar los datos al servidor');
        }
        return response.json(); // Si el servidor responde con JSON, parsea la respuesta
    })
    .then(data => {
        // Manejar la respuesta del servidor si es necesario
        console.log('Datos enviados correctamente:', data);
    })
    .catch(error => {
        // Manejar cualquier error que ocurra durante la solicitud
        console.error('Error al enviar datos:', error);
    });

