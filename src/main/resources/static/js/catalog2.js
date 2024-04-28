const carousel2 = document.querySelector(".carousel2");
const arrowBtns2 = document.querySelectorAll(".wrapper2 .arrows2");
const firstCardWidth2 = carousel2.querySelector(".card").offsetWidth;
const carouselChildren2 = [...carousel2.children];

let isDragging2 = false, startX2, startScrollLeft2;

let cardPerView2 = Math.round(carousel2.offsetWidth / firstCardWidth2);

carouselChildren2.slice(-cardPerView2).reverse().forEach(card => {
    carousel2.insertAdjacentHTML("afterbegin", card.outerHTML);
});

carouselChildren2.slice(0, cardPerView2).forEach(card => {
    carousel2.insertAdjacentHTML("beforeend", card.outerHTML);
});

arrowBtns2.forEach(btn => {
    btn.addEventListener("click", () => {
        carousel2.scrollLeft += btn.id === "left2" ? -firstCardWidth2 : firstCardWidth2;
    })
});
const dragStart2 = (e) =>{
    isDragging2 = true;
    carousel2.classList.add("dragging2")
    startX2 = e.pageX;
    startScrollLeft2 = carousel2.scrollLeft;
}
const dragging2 = (e) =>{
    if(!isDragging2) return;
    carousel2.scrollLeft = startScrollLeft2 - (e.pageX - startX2);
}
const dragStop2 = () =>{
    isDragging2 = false;
    carousel2.classList.remove("dragging2");
}
const infiniteScroll2 = () => {
    if(carousel2.scrollLeft === 0)
    {
        carousel2.classList.add("no-transition2");
        carousel2.scrollLeft = carousel2.scrollWidth - (2 * carousel2.offsetWidth);
        carousel2.classList.remove("no-transition2");
    }else if (Math.ceil(carousel2.scrollLeft) === carousel2.scrollWidth - carousel2.offsetWidth) {
        carousel2.classList.add("no-transition2");
        carousel2.scrollLeft = carousel2.offsetWidth;
        carousel2.classList.remove("no-transition2");
    }
}
carousel2.addEventListener("mousedown", dragStart2);
carousel2.addEventListener("mousemove", dragging2);
document.addEventListener("mouseup", dragStop2);
carousel2.addEventListener("scroll", infiniteScroll2);
