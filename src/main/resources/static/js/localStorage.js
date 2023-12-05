function saveLocalStorage() {
    localStorage.setItem('switchDraw', document.getElementById('toggleDibuixar').checked);
    localStorage.setItem('lasFigureSelect', document.getElementById('selectorFigura').value);
    localStorage.setItem('nombre', document.getElementById('nomDibuix').value);
    localStorage.setItem('color', document.getElementById('color').value);
    localStorage.setItem('tamaño', document.getElementById('tamaño').value);
    localStorage.setItem('rellenoFigura', document.getElementById('rellenoFigura').checked);
    localStorage.setItem('switchPublic',document.getElementById('toggleVisibility').checked)
}

document.getElementById('toggleDibuixar').addEventListener('change', saveLocalStorage);
document.getElementById('selectorFigura').addEventListener('change', saveLocalStorage);
document.getElementById('nomDibuix').addEventListener('input', saveLocalStorage);
document.getElementById('color').addEventListener('input', saveLocalStorage);
document.getElementById('tamaño').addEventListener('input', saveLocalStorage);
document.getElementById('rellenoFigura').addEventListener('change', saveLocalStorage);
document.getElementById('toggleVisibility').addEventListener('change', saveLocalStorage);

window.addEventListener('load', () => {
    document.getElementById('toggleDibuixar').checked = localStorage.getItem('switchDraw') === 'true';
    document.getElementById('selectorFigura').value = localStorage.getItem('lasFigureSelect') || 'sinSeleccionar';
    document.getElementById('nomDibuix').value = localStorage.getItem('nombre') || '';
    document.getElementById('color').value = localStorage.getItem('color') || '#000000';
    document.getElementById('tamaño').value = localStorage.getItem('tamaño') || '50';
    document.getElementById('rellenoFigura').checked = localStorage.getItem('rellenoFigura') === 'true';
    document.getElementById('toggleVisibility').checked = localStorage.getItem('switchPublic') === 'true';
});
