let dealerSum = 0;
let yourSum = 0;

let dealerAceCount = 0;
let yourAceCount = 0; 

let hidden;
let deck;

let canHit = true;

var c = 1;
var hiddenCard = []

window.onload = function() { //commento prova
    buildDeck();
    shuffleDeck();
    startGame();
}

function buildDeck() {
    let values = ["A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"];
    let types = ["cuori", "quadri", "fiori", "picche"];
    deck = [];

    for (let i = 0; i < types.length; i++) {
        for (let j = 0; j < values.length; j++) {
            deck.push(values[j] + "-" + types[i]); 
        }
    }

}

function shuffleDeck() {
    for (let i = 0; i < deck.length; i++) {
        let j = Math.floor(Math.random() * deck.length); 
        let temp = deck[i];
        deck[i] = deck[j];
        deck[j] = temp;
    }
}

function startGame() {
    document.getElementById('hit').classList.remove('nascosta')
    document.getElementById('stay').classList.remove('nascosta')
    hidden = deck.pop();
    dealerSum += getValue(hidden);
    dealerAceCount += checkAce(hidden);
    while (dealerSum < 17) {
        let cardImg = document.createElement("img");
        let card = deck.pop();
        cardImg.src = "./cards/" + card + ".png";
        dealerSum += getValue(card);
        dealerAceCount += checkAce(card);
        if (c > 1) {
            cardImg.className = 'nascosta'
            hiddenCard.push(cardImg)
        }
        document.getElementById("dealer-cards").append(cardImg);
        c++;
    }

    for (let i = 0; i < 2; i++) {
        let cardImg = document.createElement("img");
        let card = deck.pop();
        cardImg.src = "./cards/" + card + ".png";
        yourSum += getValue(card);
        yourAceCount += checkAce(card);
        document.getElementById("your-cards").append(cardImg);
        if (reduceAce(yourSum, yourAceCount) > 21) {
            yourSum = reduceAce(yourSum, yourAceCount)
        }
        if (yourAceCount == 1 & yourSum == 21 & i != 0) {
            stay()
        }
    }
    document.getElementById("your-sum").innerText = yourSum;
    document.getElementById("hit").addEventListener("click", hit);
    document.getElementById("stay").addEventListener("click", stay);

}

function hit() {
    if (!canHit) {
        return;
    }
    let cardImg = document.createElement("img");
    let card = deck.pop();
    cardImg.src = "./cards/" + card + ".png";
    yourSum += getValue(card);
    yourAceCount += checkAce(card);
    document.getElementById("your-cards").append(cardImg);
    var sumVar = reduceAce(yourSum, yourAceCount)
    if (sumVar != yourSum) {
        yourSum = sumVar
        yourAceCount--
    }
    document.getElementById("your-sum").innerText = yourSum;
    if (yourSum >= 21) {
        stay()
    }
}

function stay() {
    dealerSum = reduceAce(dealerSum, dealerAceCount);
    yourSum = reduceAce(yourSum, yourAceCount);
    for(var i = 0; i < hiddenCard.length; i++) {
        hiddenCard[i].className = ''
    }

    canHit = false;
    document.getElementById("hidden").src = "./cards/" + hidden + ".png";

    let message = "";
    if (yourSum > 21) {
        message = "You Lose!";
    }
    else if (dealerSum > 21) {
        message = "You win!";
    }
 
    else if (yourSum == dealerSum) {
        message = "Tie!";
    }
    else if (yourSum > dealerSum) {
        message = "You Win!";
    }
    else if (yourSum < dealerSum) {
        message = "You Lose!";
    }
    // Mostra il messaggio personalizzato
    showCustomAlert(message);
    // Nascondi i pulsanti "Carta" e "Stai", mostra "Rigioca"
    document.getElementById('hit').classList.add('nascosta');
    document.getElementById('stay').classList.add('nascosta');
    document.getElementById('rigioca').classList.remove('nascosta');
    // Aggiungi il listener per il pulsante "Rigioca"
    document.getElementById('rigioca').addEventListener('click', function() {
        location.reload(); // Ricarica la pagina per rigiocare
    });
    /*document.getElementById("dealer-sum").innerText = dealerSum;
    document.getElementById("your-sum").innerText = yourSum;
    document.getElementById("results").innerText = message;
    document.getElementById('hit').classList.add('nascosta')
    document.getElementById('stay').classList.add('nascosta')
    var rigioca = document.createElement('button')
    rigioca.textContent = 'rigioca'
    document.getElementsByTagName('body')[0].appendChild(rigioca)
    rigioca.addEventListener('click', function() {
        location.reload()
    })*/
}

function showCustomAlert(message) {
    document.getElementById("alert-message").innerText = message;
    document.getElementById("custom-alert").classList.remove('hidden');
    // Chiudi l'alert quando si preme il pulsante
    document.getElementById("close-alert").onclick = function() {
        document.getElementById("custom-alert").classList.add('hidden');
    }
}

function getValue(card) {
    let data = card.split("-"); 
    let value = data[0];

    if (isNaN(value)) { 
        if (value == "A") {
            return 11;
        }
        return 10;
    }
    return parseInt(value);
}

function checkAce(card) {
    if (card[0] == "A") {
        return 1;
    }
    return 0;
}

function reduceAce(playerSum, playerAceCount) {
    while (playerSum > 21 && playerAceCount > 0) {
        playerSum -= 10;
        playerAceCount -= 1;
    }
    return playerSum;
}