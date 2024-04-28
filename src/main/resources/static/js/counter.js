
const counters = document.querySelectorAll('[quantity_counter]');
var inputs = document.querySelectorAll(".counter_input input");
var prices = document.querySelectorAll(".product_price");
var sum_input = document.querySelector(".sum input");
var number_input = document.querySelector(".number input");
var sum = 0;
var number = 0;
for (var i = 0; i <inputs.length; i++) {
  number += Number(inputs[i].value);
}
for (var i = 0; i <prices.length; i++) {
  sum += Number(inputs[i].value) * Number(prices[i].innerText.substring(6, prices[i].innerText.length-1));
}
if(!isNaN(sum))
{
  sum_input.value = sum + "₽";
}
if(!isNaN(number))
{
  number_input.value = number + " шт.";
}
if (counters)
{
    counters.forEach(counter => {
        counter.addEventListener('click', e =>{
            const target = e.target;
            if(target.closest(".plus_button")){
                let value = parseInt(target.closest('.counter').querySelector('input').value);
                value++;
                if(value >= 10)
                {
                    value = 10;
                }
                target.closest('.counter').querySelector('input').value = value;
            }
            else if(target.closest(".minus_button")){
                let value = parseInt(target.closest('.counter').querySelector('input').value);
                value--;
                if(value <= 1)
                {
                    value = 1;
                }
                console.log(number);
                target.closest('.counter').querySelector('input').value = value;
            }
            number = 0;
            for (var i = 0; i <inputs.length; i++) {
              number += Number(inputs[i].value);
            }

            sum = 0;
            for (var i = 0; i <prices.length; i++) {
              sum += Number(inputs[i].value) * Number(prices[i].innerText.substring(6, prices[i].innerText.length-1));
            }

            if(!isNaN(sum))
            {
              sum_input.value = sum + "₽";
            }
            if(!isNaN(number))
            {
              number_input.value = number + " шт.";
            }


        })
    })
}