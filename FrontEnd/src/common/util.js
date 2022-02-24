export default {
  makeDateStr : function(year, month, day, type){
    if( type == '.' ){
      return year + '.' + ( (month < 10) ? '0' + month : month ) + '.' + ( (day < 10) ? '0' + day : day );
    }
  },
  makeTimeStr : function(hour, minute, second, type){
    if( type == ':' ){
      return hour + ':' + ( (minute < 10) ? '0' + minute : minute ) + ':' + ( (second < 10) ? '0' + second : second );
    }
  },

  // 시세 필터 함수
  convertMoney(money) {
    money /= 1000;

    money.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");

    return Math.round(money * 100) / 100 + "억원"
  },

  // 평수 필터 함수
  convertArea(area) {
    area *= 0.3025; // 평수 계산
    return Math.round(area * 100) / 100 + "평"
  },

  // 별점 필터 함수
  convertRating(rating) {
    rating *= 20; // 0 ~ 100%값으로 바꿈

    // return값에 1.5를 더하여 주는 이유는 half star일 시 미세하게 절반이 안되어보여서 보여지는 값을 조정
    return rating + 1.5 + '%';
  },
}
