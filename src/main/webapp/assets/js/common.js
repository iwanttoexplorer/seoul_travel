<<<<<<< HEAD
/** 
입력 값이 비어 있는지 확인하는 함수
@param {any} value:입력값
@returns {boolearn}
*/
let isEmpty = function(value){
	if(null === value || value == undefined){
		return true;
	}
	if(typeof value ==='string' && value.trim()==''){
		return true;
	}
	if(Array.isArray(value) && value.length===0){
		return true;
	}
	return false;
}
=======
/**
 * 입력 값이 비어 있는지 확인하는 함수
 * @param {any} value : 입력값
 * @returns {boolean} 비어 있으면 true, 그렇지 않으면 false
 */
 
 let isEmpty = function(value){
  
  if(null === value || value == undefined){
    return true;
  }
  
  if(typeof value === 'string' && value.trim() === ''){
    return true;
  }
  
  if(Array.isArray(value) && value.length === 0){
    return true;
  }
  
  return false;
}
 
 
>>>>>>> 18608404195a497d2a72d1030600d649f5d08396
