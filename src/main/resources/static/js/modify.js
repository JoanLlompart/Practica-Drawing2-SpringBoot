const canvas = document.getElementById("lienzo");
const ctx = canvas.getContext("2d");
const figuresJSON = document.getElementById("llistaFigureJson");
const strokesJSON = document.getElementById("llistaStroke");

//guardam el tipus de seleccio que ha fet el usuari
const figura = document.getElementById("selectorFigura");
//const dibuix = document.getElementById("btnDibuixar");
const dibuix = document.getElementById("toggleDibuixar");


const borrarDibuix = document.getElementById("borrarLienzo");
const saveDraw = document.getElementById("guardarDibuix");
const sizeSelect = document.getElementById("tamaño");
const nameDraw = document.getElementById("name");

//si se ha seleccionat rellenar els poligons
const isFilled = document.getElementById("rellenoFigura");
//color que elegeix el usuari
const colorElegit = document.getElementById("color");

//variable per sebre si es pot pintar o no.
let activaPintar = false;

// Guarda los puntos que va pintando
let punts = [];



const pintar = (listStrokes) => {
    listStrokes.forEach(stroke => {
        const { puntos, color, size } = stroke;
        ctx.beginPath();
        ctx.strokeStyle = color;
        ctx.lineWidth = size;
        // Comensam a dibuixar per la posicio 0.
        ctx.moveTo(puntos[0].x, puntos[0].y);
        // Dibuixa la linea punt per punt recorrent el array
        for (let i = 1; i < puntos.length; i++) {
            ctx.lineTo(puntos[i].x, puntos[i].y);
        }
        //dibuixar els punts.
        ctx.stroke();
    });
};
const dibuixarFigure = (figures) => {
    // Itera sobre la llista de figures i dibuixa cada una de ellas.
    figures.forEach(figure => {
        const { type, color, rellenoFigura, size, centerX, centerY } = figure;
        ctx.beginPath();
        ctx.strokeStyle = color;
        ctx.lineWidth = 2;

        if (rellenoFigura) {
            ctx.fillStyle = color;
        } else {
            ctx.fillStyle = "transparent";
        }

        switch (type) {
            case "cuadrado":
                const halfSize = size / 2;
                ctx.rect(centerX - halfSize, centerY - halfSize, size, size);
                if (rellenoFigura) {
                    ctx.fill();
                } else {
                    ctx.stroke();
                }
                break;
            case "circulo":
                let radi = size / 2;
                ctx.arc(centerX, centerY, radi, 0, 2 * Math.PI);
                if (rellenoFigura) {
                    ctx.fill();
                } else {
                    ctx.stroke();
                }
                break;
            case "triangle":
                ctx.moveTo(centerX, centerY - size / 2);
                ctx.lineTo(centerX + size / 2, centerY + size / 2);
                ctx.lineTo(centerX - size / 2, centerY + size / 2);
                ctx.closePath();
                if (rellenoFigura) {
                    ctx.fill();
                } else {
                    ctx.stroke();
                }
                break;
            case "estrella":
                let radiExtern = size / 2;
                let radiIntern = radiExtern * 0.4;

                ctx.beginPath();
                ctx.moveTo(centerX, centerY - radiExtern);

                for (let i = 0; i <= 14; i++) {
                    const radio = i % 2 === 0 ? radiExtern : radiIntern;
                    const angle = (Math.PI / 7) * i;
                    const x = centerX + radio * Math.sin(angle);
                    const y = centerY - radio * Math.cos(angle);
                    ctx.lineTo(x, y);
                }

                if (rellenoFigura) {
                    ctx.fillStyle = color;
                    ctx.fill();
                } else {
                    ctx.strokeStyle = color;
                    ctx.stroke();
                }
                break;
            case "sinSeleccionar":
            default:
                break;
        }
    });
};
console.log(figuresJSON);
console.log(strokesJSON);
//convertim a la llista de figure a una llista de objectes Figure
const listFigure = JSON.parse(figuresJSON.value);
console.log(listFigure);
const listStrokes = JSON.parse(strokesJSON.value);
console.log(listStrokes.value);


const render = () => {
    const ul = document.querySelector("#liFigures");
    ul.innerHTML = "";
    drawingData.figures.forEach((item, i) => {
        // Es una figura
        ul.innerHTML += `<li>${item.type}
        <button id="${i}" onclick="removeFigure(${i})">x</button>
        </li>`;
    });
    drawingData.strokes.forEach((stoke, i) => {
        // Es un trazo (stroke)
        ul.innerHTML += `<li>Stroke
        <button id="${i}" onclick="removeStroke(${i})">x</button>
        </li>`;
    });
};
const removeFigure = (i) => {
    drawingData.figures.splice(i, 1);
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    render();
};
const removeStroke = (i) => {
    drawingData.strokes.splice(i, 1);
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    render();
};

// Definim la clase Figure.
class Drawing {
    constructor(figures, strokes, name) {
        this.name = name; //guardam el nom.
        this.figures = []; // Guarda les dades de les figures
        this.strokes = []; // Guarda les dades de els dibuixos a ma alçada.
    }
    addFigure(type, color, rellenoFigura, size, centerX, centerY) {
        this.figures.push({ type, color, rellenoFigura, size, centerX, centerY });
        render(this.figures);
    }
    addStroke(puntos, color, size) {
        this.strokes.push({ puntos, color, size });
        render(this.strokes);//AMB PROBAS DE FER LA LLISTA DINAMICA
    }
    //proba de tornar 2 json
    getFiguresJSON() {
        return JSON.stringify(this.figures);
    }
    getStrokesJSON() {
        return JSON.stringify(this.strokes);
    }

    // Metode per eliminar una figura per índice
    deleteAll() {
        this.figures.splice(0, this.figures.length);
        this.strokes.splice(0, this.strokes.length)
        render(this.figures);
        render(this.strokes);//proba
    }
}

const drawingData = new Drawing();
drawingData.addFigure(listFigure);
drawingData.addStroke(listStrokes);




dibuixarFigure(listFigure);
pintar(listStrokes);

//proba que fallara segur

const mouseDown = (event) => {
    if (activaPintar) {
        //li pasam les variables de el punt de partida a la funcio pintar
        pintar(event.offsetX, event.offsetY);
        canvas.addEventListener("mousemove", mouseMoving);
    }
};

//funcio per detectar el moviment de el ratoli
const mouseMoving = (event) => {
    const punt = { x: event.offsetX, y: event.offsetY };
    punts.push(punt);
    pintar(event.offsetX, event.offsetY);

};
// detecte cuant deixam de fer click
const mouseUp = (e) => {
    //console.log(e);
    if (activaPintar) {
        canvas.removeEventListener("mousemove", mouseMoving);
        drawingData.addStroke(punts, colorElegit.value, sizeSelect.value);
        punts = [];
    }
    //console.log(drawingData.strokes);
}

//Escoltam el event cuant faci el usuari clic i es mantengui cridarar a la funcio.
canvas.addEventListener("mousedown", mouseDown);
canvas.addEventListener("mouseup", mouseUp);
canvas.addEventListener("click", dibuixarFigure);

saveDraw.addEventListener("click", () => {
    const figuresJson = drawingData.getFiguresJSON();
    const strokesJson = drawingData.getStrokesJSON();

    //FALTA CAMBIAR EL JSP PER POSAR DOS HIDDEN
    document.getElementById("figuresData").value = figuresJson;
    document.getElementById("strokesData").value = strokesJson;

    document.getElementById("drawForm").submit();
    // Por ejemplo, si estás utilizando un formulario, puedes enviarlo así:
    // document.getElementById("drawForm").submit();

    console.log("Figures JSON:", figuresJson);
    console.log("Strokes JSON:", strokesJson);
});


// Asocia el botó "Dibujar" amb la función de activar el pintar
//dibuix.addEventListener("click", activarDibuix);
dibuix.addEventListener("change", (event) => {
    // Activa la capacidad de dibuixar cuando el toggle switch esté activado
    activaPintar = event.target.checked;
});

/*
dibuix.addEventListener("click", (event) => {
    // Activa la capacidad de dibuixar en fer  clic en el botó "Dibuixar"
    activaPintar = !activaPintar;
});
*/

canvas.addEventListener("mouseout", () => {
    console.log("mouseout");
    ctx.closePath();
});

//borrar el canvas
function borrarLienzo() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawingData.deleteAll();
}
