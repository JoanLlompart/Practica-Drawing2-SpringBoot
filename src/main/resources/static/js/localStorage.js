function saveLocalStorage() {
    localStorage.setItem('switch', document.getElementById('toggleDibuixar').checked);
    localStorage.setItem('ultimaFigura', document.getElementById('selectorFigura').value);
    localStorage.setItem('nombre', document.getElementById('nomDibuix').value);
    localStorage.setItem('color', document.getElementById('color').value);
    localStorage.setItem('tamaño', document.getElementById('tamaño').value);
    localStorage.setItem('rellenoFigura', document.getElementById('rellenoFigura').checked);
    localStorage.setItem('')
}

document.getElementById('toggleDibuixar').addEventListener('change', saveLocalStorage);
document.getElementById('selectorFigura').addEventListener('change', saveLocalStorage);
document.getElementById('nomDibuix').addEventListener('input', saveLocalStorage);
document.getElementById('color').addEventListener('input', saveLocalStorage);
document.getElementById('tamaño').addEventListener('input', saveLocalStorage);
document.getElementById('rellenoFigura').addEventListener('change', saveLocalStorage);

window.addEventListener('load', () => {
    document.getElementById('toggleDibuixar').checked = localStorage.getItem('switch') === 'true';
    document.getElementById('selectorFigura').value = localStorage.getItem('ultimaFigura') || 'sinSeleccionar';
    document.getElementById('nomDibuix').value = localStorage.getItem('nombre') || '';
    document.getElementById('color').value = localStorage.getItem('color') || '#000000';
    document.getElementById('tamaño').value = localStorage.getItem('tamaño') || '50';
    document.getElementById('rellenoFigura').checked = localStorage.getItem('rellenoFigura') === 'true';
});
