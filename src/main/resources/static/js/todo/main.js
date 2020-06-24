const timer = () => {
    const clock = document.querySelector('#clock');
    const today = new Date();
    let h = today.getHours();
    if (h == 0) {
        h = 12;
    }
    (h > 12) ? h = 'PM ' + (h-12) : h = 'AM ' + h;
    clock.innerHTML =
        `${addZero(h)} : ${addZero(today.getMinutes())} :
        ${addZero(today.getSeconds())}`;
}

const addZero = (num) => {
    (num < 10) ? num = '0' + num : num;
    return num;
}

const start = () => {
  timer();
  setInterval(timer, 1000);
}

window.onload = start();