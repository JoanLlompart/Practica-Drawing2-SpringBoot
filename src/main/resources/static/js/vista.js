const canvas = document.getElementById("lienzo");
const ctx = canvas.getContext("2d");
const figuresJSON = document.getElementById("llistaFigureJson");
const strokesJSON = document.getElementById("llistaStroke");
const select = document.querySelector("select"); 

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

dibuixarFigure(listFigure);
pintar(listStrokes);

/*
const versionSelect = document.getElementById("versionSelect");

versionSelect.addEventListener("change",function() {
    const valueSelect = versionSelect.value;
    console.log("Valor num " + valueSelect);
    
});
*/