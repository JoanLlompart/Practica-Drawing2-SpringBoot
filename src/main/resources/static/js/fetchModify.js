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



canvas.addEventListener("mouseout", () => {
    console.log("mouseout");

    // Si el temporizador está activo (previene múltiples temporizadores)
    if (timerId) {
        clearTimeout(timerId);
    }

    // Iniciar un temporizador para guardar después de 20 segundos
    timerId = setTimeout(() => {
        console.log("Han pasado 20 segundos desde mouseout, guardando los datos...");
        guardarDatos(); // Esta función debería contener tu lógica para enviar los datos al servidor
    }, 20000); // 20 segundos (20000 milisegundos)
});

// Cancelar el temporizador si el usuario regresa al canvas antes de 20 segundos
canvas.addEventListener("mouseenter", () => {
    if (timerId) {
        clearTimeout(timerId);
        timerId = null;
    }
});

const guardarDatos = () => {
// Obtener los datos que deseas enviar al servidor
const figuresData = document.getElementById('llistaFigureJson').value;
const strokesData = document.getElementById('llistaStroke').value;

console.log("figuresData" + figuresData);
console.log("strokesData "+ strokesData);
// Datos adicionales que quieras enviar
const nameCanvas = document.getElementById('nomDibuix').value;
console.log("name canvas" + nameCanvas);
const isPublic = document.getElementById('toggleVisibility').checked;

console.log("is public? " + isPublic);

// Crear el objeto con los datos a enviar
const data = {
    strokesData: strokesData,
    figuresData: figuresData,
    nomDibuix: nameCanvas,
    isPublic: isPublic
};
console.log(data);
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
};
