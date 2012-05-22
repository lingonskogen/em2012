<%@ include file="./views/includes/header.jsp"%>

<div class="row">
  <div class="span12">
    <section>
    <h2>Resultat</h2>
    <table class="table table-striped">
      <tr>
        <th colspan="10" style="text-align: center;"><h4>Match <small>1 - 8</small><h4></th>
      </tr>
      
      <tr>
      <td /><th>Avspark</th>
      <c:forEach items="${data.gameFormDataList1}" var="gameFormData"
        varStatus="status">
          <td style="text-align: center;">${gameFormData.kickoff}</td>
      </c:forEach>
      </tr>
      
      <tr>
      <td /><th>Hemma</th>
      <c:forEach items="${data.gameFormDataList1}" var="gameFormData"
        varStatus="status">
          <td style="text-align: center;"><img src="/images/${gameFormData.homeTeam.code}.png" /><br />${gameFormData.homeTeam.name}</td>
      </c:forEach>
      </tr>
      
      <tr>
      <td /><th>Borta</th>
      <c:forEach items="${data.gameFormDataList1}" var="gameFormData"
        varStatus="status">
          <td style="text-align: center;"><img src="/images/${gameFormData.awayTeam.code}.png" /><br />${gameFormData.awayTeam.name}</td>
      </c:forEach>
      </tr>

      <tr>
      <td /><th>Resultat</th>
      <c:forEach items="${data.gameFormDataList1}" var="gameFormData"
        varStatus="status">
          <th style="text-align: center;">${gameFormData.game.homeScore} - ${gameFormData.game.awayScore}</th>
      </c:forEach>
      </tr>
      
      <c:forEach items="${data.userScoreDataList}" var="userScoreData">
      <tr>
        <td>${userScoreData.score}</td>
        <td>${userScoreData.user.realName}</td>
	      <c:forEach items="${userScoreData.predictionList1}" var="predictionScore"
	        varStatus="status">
	          <td class="score${predictionScore.score} }" style="text-align: center;">${predictionScore.prediction.homeScore} - ${predictionScore.prediction.awayScore}</td>
	      </c:forEach>
      </tr>
      </c:forEach>
        
      <tr>
        <th colspan="10" style="text-align: center;"><h4>Match <small>9 - 16</small><h4></th>
      </tr>
      
      <tr>
      <td /><th>Avspark</th>
      <c:forEach items="${data.gameFormDataList2}" var="gameFormData"
        varStatus="status">
          <td style="text-align: center;">${gameFormData.kickoff}</td>
      </c:forEach>
      </tr>
      
      <tr>
      <td /><th>Hemma</th>
      <c:forEach items="${data.gameFormDataList2}" var="gameFormData"
        varStatus="status">
          <td style="text-align: center;"><img src="/images/${gameFormData.homeTeam.code}.png" /><br />${gameFormData.homeTeam.name}</td>
      </c:forEach>
      </tr>
      
      <tr>
      <td /><th>Borta</th>
      <c:forEach items="${data.gameFormDataList2}" var="gameFormData"
        varStatus="status">
          <td style="text-align: center;"><img src="/images/${gameFormData.awayTeam.code}.png" /><br />${gameFormData.awayTeam.name}</td>
      </c:forEach>
      </tr>

      <tr>
      <td /><th>Resultat</th>
      <c:forEach items="${data.gameFormDataList2}" var="gameFormData"
        varStatus="status">
          <th style="text-align: center;">${gameFormData.game.homeScore} - ${gameFormData.game.awayScore}</th>
      </c:forEach>
      </tr>
      
      <c:forEach items="${data.userScoreDataList}" var="userScoreData">
      <tr>
        <td>${userScoreData.score}</td>
        <td>${userScoreData.user.realName}</td>
        <c:forEach items="${userScoreData.predictionList2}" var="predictionScore"
          varStatus="status">
            <td class="score${predictionScore.score} }" style="text-align: center;">${predictionScore.prediction.homeScore} - ${predictionScore.prediction.awayScore}</td>
        </c:forEach>
      </tr>
      </c:forEach>
        
      <tr>
        <th colspan="10" style="text-align: center;"><h4>Match <small>17 - 24</small><h4></th>
      </tr>
      
      <tr>
      <td /><th>Avspark</th>
      <c:forEach items="${data.gameFormDataList3}" var="gameFormData"
        varStatus="status">
          <td style="text-align: center;">${gameFormData.kickoff}</td>
      </c:forEach>
      </tr>
      
      <tr>
      <td /><th>Hemma</th>
      <c:forEach items="${data.gameFormDataList3}" var="gameFormData"
        varStatus="status">
          <td style="text-align: center;"><img src="/images/${gameFormData.homeTeam.code}.png" /><br />${gameFormData.homeTeam.name}</td>
      </c:forEach>
      </tr>
      
      <tr>
      <td /><th>Borta</th>
      <c:forEach items="${data.gameFormDataList3}" var="gameFormData"
        varStatus="status">
          <td style="text-align: center;"><img src="/images/${gameFormData.awayTeam.code}.png" /><br />${gameFormData.awayTeam.name}</td>
      </c:forEach>
      </tr>

      <tr>
      <td /><th>Resultat</th>
      <c:forEach items="${data.gameFormDataList3}" var="gameFormData"
        varStatus="status">
          <th style="text-align: center;">${gameFormData.game.homeScore} - ${gameFormData.game.awayScore}</th>
      </c:forEach>
      </tr>
      
      <c:forEach items="${data.userScoreDataList}" var="userScoreData">
      <tr>
        <td>${userScoreData.score}</td>
        <td>${userScoreData.user.realName}</td>
        <c:forEach items="${userScoreData.predictionList3}" var="predictionScore"
          varStatus="status">
            <td class="score${predictionScore.score} }" style="text-align: center;">${predictionScore.prediction.homeScore} - ${predictionScore.prediction.awayScore}</td>
        </c:forEach>
      </tr>
      </c:forEach>
        
    </table>
    </section>
  </div>
</div>

<%@ include file="./views/includes/footer.jsp"%>