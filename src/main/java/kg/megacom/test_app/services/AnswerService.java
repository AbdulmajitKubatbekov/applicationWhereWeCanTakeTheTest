package kg.megacom.test_app.services;

import kg.megacom.test_app.models.dto.AnswerDto;

import kg.megacom.test_app.models.dto.QuestionDto;
import kg.megacom.test_app.models.entities.Answer;
import kg.megacom.test_app.models.entities.Question;

import java.util.List;

public interface AnswerService {

    AnswerDto save(AnswerDto answerDto);

    AnswerDto findById(Long id);

    AnswerDto update(AnswerDto answerDto);

    AnswerDto delete(AnswerDto answerDto);

    List<AnswerDto> findAllByQuestion(QuestionDto questionDto);
    List<AnswerDto> findAllByQuestionAndTrue(QuestionDto questionDto);

}
