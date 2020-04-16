let x = 1+1; // блочный доступ ( локальные)
const x2 = true; // константы
var sum = x + x * 2;
console.log("2+2*2=", sum);


let karl = "Карл";
let text = `${karl} у Клары украл коралы,
            а клара у ${karl}а украла кларнет`;
console.log(text);

// условия
// == - приводятся к одному типу
// === - приводяться к разным типам
if (x < 100) {
    x += 100;
}else {
    x += 1;
}
console.log(x)

var x4 = "2"
switch (x) {
    case "0":
        console.log("x=0");
        break;
    case "1":
        console.log("x=1");
        break;
    default:
        console.log("x=" + x);
        break;

}

for (let i = 0; i < 4; i++) {
    console.log(i * i);
}

{
    let x = 0;
    while (x++ < 4) {
        console.log(x);
    }
}