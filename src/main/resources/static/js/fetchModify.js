


canvas.addEventListener("mouseout", () => {
    console.log("mouseout");

    if (timerId) {
        clearTimeout(timerId);
    }

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

//NOU PER SI SURT EL USUARI SENSE GUARDAR
window.addEventListener("beforeunload", async (event) => {

    event.preventDefault();

    // Guardar los datos antes de que el usuario abandone la página
    guardarDatos();

    // Mostrar un mensaje al usuario antes de abandonar la página
    event.returnValue = ''; // Este mensaje puede variar según el navegador y no se muestra en todos
});




const guardarDatos = () => {
// Obtener los datos que deseas enviar al servidor
const figuresData = document.getElementById('llistaFigureJson').value;
const strokesData = document.getElementById('llistaStroke').value;

console.log("figuresData" + figuresData);
console.log("strokesData "+ strokesData);

const nameCanvas = document.getElementById('nomDibuix').value;
console.log("name canvas" + nameCanvas);
const isPublic = document.getElementById('toggleVisibility').checked;

console.log("is public? " + isPublic);


const data = {
    strokesData: strokesData,
    figuresData: figuresData,
    nomDibuix: nameCanvas,
    isPublic: isPublic
};
console.log(data);
fetch('/modify', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json' 
    },
    body: JSON.stringify(data) 
})
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al enviar los datos al servidor');
        }
        return response.json(); 
    })
    .then(data => {

        console.log('Datos enviados correctamente:', data);
    })
    .catch(error => {
        console.error('Error al enviar datos:', error);
    });
};
