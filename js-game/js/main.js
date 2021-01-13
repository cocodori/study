const GAME_TIME = 15;
let score = 0;
let time = GAME_TIME;
let isPlaying = false;
let timeInterval;
let words = [];
let checkInterval;

const wordInput = document.querySelector('.word-input');
const wordDisplay = document.querySelector('.word-display');
const scoreDisplay = document.querySelector('.score');
const timeDisplay = document.querySelector('.time');
const button = document.querySelector('.button');

init();

function init() {
    wordInput.addEventListener('input', checkMatch);
    getWords()
 }

 //단어 불러오기
function getWords() {
    axios.get('https://random-word-api.herokuapp.com/word?number=100')
    .then(function (response) {
        response.data.forEach((word) => {
            if (word.length < 10) {
                words.push(word);
            }
        })
    })
  .catch(function (error) {
    console.log(error);
  })

    buttonChange('게임시작')
}

//단어가 일치하는지 체크
function checkMatch() {
    if(wordInput.value === wordDisplay.innerText) {
        if(!isPlaying) return;
        wordInput.value = "";
        score++;
        scoreDisplay.innerText = score;
        const randomIndex = Math.floor(Math.random() * words.length);
        wordDisplay.innerText = words[randomIndex];
    }
}

//게임 실행
function run(){
    isPlaying = true;
    wordDisplay.innerText = words[0];
    wordInput.value="";
    wordInput.focus();
    score=0;
    time = GAME_TIME;
    scoreDisplay.innerText = 0;
    timeInterval =  setInterval(countDown, 1000); 
    checkInterval = setInterval(checkStatus, 50);
    buttonChange('게임 중...')
}

function countDown() {
    time > 0 ? time-- : isPlaying = false;
    timeDisplay.innerText = time;

    if(!isPlaying) {
        clearInterval(timeInterval);
    }
}

function checkStatus() {
    if(!isPlaying && time === 0) {
        isPlaying = false;
        buttonChange('게임시작')
        clearInterval(checkInterval)
        wordDisplay.innerText = '당신의 점수는' + score +'점 입니다.'
    }
}

function buttonChange(text) {
    button.innerText = text;
    text==='게임시작' ? button.classList.remove('loading') : button.classList.add('loading')
}