$(function  () {
	var height=$('.fixedTitle')[0].offsetTop;
	console.log(height)
	$(window).on('scroll', function(){ 
		if($(window).scrollTop()>=height){
			$('.fixedTitle2').show()
		}else{
			$('.fixedTitle2').hide()
		}
	
	})

	$('.answerItem').on('click',function(){		
		if($(this).parents('.item').hasClass('active')){
			$(this).parents('.item').next().removeClass('noneAnswer').addClass('active')
			$(window).scrollTop($(this).parents('.item')[0].offsetTop+100)
		}
		
		$(this).parents('.item').find('.answerItem').removeClass('active')
		$(this).addClass('active');
		$(this).parents('.item').removeClass('active').addClass('answered');

		
	})
	//提交   //是a 基本b 倾向 c

	$('.btn').click(function(){
		
		if($('.item.active').length||$('.item.noneAnswer').length){
			alert('请先写完再提交')
		}else{
			var rObj={};
			var A=[],B=[],C=[],D=[],E=[],F=[],G=[],H=[],I=[];
			var AScore=0,BScore=0,CScore=0,DScore=0,EScore=0,FScore=0,GScore=0,HScore=0,IScore=0;
			for(var i=0;i<$('.answerItem.active').length;i++){
				if(i>=0&&i<8){//A
					A.push($('.answerItem.active').eq(i).attr('score'));
					AScore+=Number($('.answerItem.active').eq(i).attr('score'));
				}else if(i>=8&&i<16){//B
					B.push($('.answerItem.active').eq(i).attr('score'));
					BScore+=Number($('.answerItem.active').eq(i).attr('score'));
				}else if(i>=16&&i<23){//C
					C.push($('.answerItem.active').eq(i).attr('score'));
					CScore+=Number($('.answerItem.active').eq(i).attr('score'));
				}else if(i>=23&&i<31){//D
					D.push($('.answerItem.active').eq(i).attr('score'));
					DScore+=Number($('.answerItem.active').eq(i).attr('score'));
				}else if(i>=31&&i<39){//E
					E.push($('.answerItem.active').eq(i).attr('score'));
					EScore+=Number($('.answerItem.active').eq(i).attr('score'));
				}else if(i>=39&&i<45){//F
					F.push($('.answerItem.active').eq(i).attr('score'));
					FScore+=Number($('.answerItem.active').eq(i).attr('score'));
				}else if(i>=45&&i<52){//G
					G.push($('.answerItem.active').eq(i).attr('score'));
					GScore+=Number($('.answerItem.active').eq(i).attr('score'));
				}else if(i>=52&&i<59){//H
					H.push($('.answerItem.active').eq(i).attr('score'));
					HScore+=Number($('.answerItem.active').eq(i).attr('score'));
				}else if(i>=59&&i<66){//I
					I.push($('.answerItem.active').eq(i).attr('score'));
					IScore+=Number($('.answerItem.active').eq(i).attr('score'));
				}
			}

			var obj={
			    ATScore:Math.round((AScore-A.length)/(A.length*4)*100),
			    BTScore:Math.round((BScore-B.length)/(B.length*4)*100),
			    CTScore:Math.round((CScore-C.length)/(C.length*4)*100),
			    DTScore:Math.round((DScore-D.length)/(D.length*4)*100),
			    ETScore:Math.round((EScore-E.length)/(E.length*4)*100),
			    FTScore:Math.round((FScore-F.length)/(F.length*4)*100),
			    GTScore:Math.round((GScore-G.length)/(G.length*4)*100),
			    HTScore:Math.round((HScore-H.length)/(H.length*4)*100),
			    ITScore:Math.round((IScore-I.length)/(I.length*4)*100)
			}	
			console.log(obj)	
			

			if(obj.ATScore>=60){
				if(obj.BTScore<30&&obj.CTScore<30&&obj.DTScore<30&&obj.ETScore<30&&obj.FTScore<30&&obj.GTScore<30&&obj.HTScore<30&&obj.ITScore<30){
					rObj={};
					rObj.main='aATScore';
					
				}else{
					if(obj.BTScore<40&&obj.CTScore<40&&obj.DTScore<40&&obj.ETScore<40&&obj.FTScore<40&&obj.GTScore<40&&obj.HTScore<40&&obj.ITScore<40){
						rObj={};
						rObj.main='bATScore'
						var max=Math.max(obj.BTScore,obj.CTScore,obj.DTScore,obj.ETScore,obj.FTScore,obj.GTScore,obj.HTScore,obj.ITScore)
						if(max>=30){
							for(var i in obj){
								if(i!='ATScore'){
									if(obj[i]==max){
										rObj.sub='c'+i
									}
								}
								
							}
						}
						
						
					}else{
						rObj={};
					
						var max=Math.max(obj.BTScore,obj.CTScore,obj.DTScore,obj.ETScore,obj.FTScore,obj.GTScore,obj.HTScore,obj.ITScore)
						if(max>=40){
							
							var arr=[];
							for(var i in obj){
								if(obj[i]==max){
									rObj.main='a'+i;
								}else{
									if(i!='ATScore'){
										arr.push(obj[i])
									}
								}

							}

							var max2=Math.max.apply(null, arr);
							if(max2>=30&&max<90){
								for(var i in obj){
									if(obj[i]==max2){
										rObj.sub='c'+i
									}

								}
							}
							


						}
					}
				}
			}else{
				rObj={};
				var max=Math.max(obj.BTScore,obj.CTScore,obj.DTScore,obj.ETScore,obj.FTScore,obj.GTScore,obj.HTScore,obj.ITScore)
				if(max>=40){
					
					var arr=[];
					for(var i in obj){
						if(obj[i]==max){
							rObj.main='a'+i;
						}else{
							if(i!='ATScore'){
								arr.push(obj[i])
							}
						}

					}

					var max2=Math.max.apply(null, arr);
					if(max2>=30&&max<90){
						for(var i in obj){
							if(obj[i]==max2){
								rObj.sub='c'+i
							}

						}
					}
					


				}
					
				
			}

			console.log(rObj)
			
			var str='';
			for(var i in rObj){
				str+=i+'='+rObj[i]+'&';
			}
			str=str.slice(0,-1);
			console.log(str)
			if(!str){
				alert('回答错误，请重新填写');
				return;
			}
			location.href="detail.html?"+str;
		}
		
	})
})



	// var aaa={main:"aBTScore",sub:'cCTSore'}
	// $('#abc').on('click',function(){
	// 	var str='';
	// 	for(var i in aaa){
	// 		str+=i+'='+aaa[i]+'&';
	// 	}
	// 	str=str.slice(0,-1);
	// 	console.log(str)
	// 	location.href="detail.html?"+str;
	// })
	