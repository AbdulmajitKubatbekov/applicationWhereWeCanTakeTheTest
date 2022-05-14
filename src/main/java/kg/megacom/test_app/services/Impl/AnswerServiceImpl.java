package kg.megacom.test_app.services.Impl;

import kg.megacom.test_app.dao.AnswerDao;
import kg.megacom.test_app.mappers.AnswerMapper;
import kg.megacom.test_app.mappers.QuestionMapper;
import kg.megacom.test_app.models.dto.AnswerDto;
import kg.megacom.test_app.models.dto.QuestionDto;
import kg.megacom.test_app.models.entities.Answer;
import kg.megacom.test_app.models.entities.Question;
import kg.megacom.test_app.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDao answerDao;
    private AnswerMapper answerMapper = AnswerMapper.INSTANCE;
    private QuestionMapper questionMapper = QuestionMapper.INSTANCE;

    @Override
    public AnswerDto save(AnswerDto answerDto) {
        Answer answer = answerMapper.answerDtoToAnswer(answerDto);
        answer.setActive(true);
        Answer answerSaved = answerDao.save(answer);
        return answerMapper.answerToAnswerDto(answerSaved);
    }

    @Override
    public AnswerDto findById(Long id) {
        Answer answer = answerDao.findById(id).orElse(null);
        return answerMapper.answerToAnswerDto(answer);
    }

    @Override
    public AnswerDto update(AnswerDto answerDto) {
        boolean isExists = answerDao.existsById(answerDto.getId());
        if (!isExists){
            return null;
        }else{
            Answer answer = answerMapper.answerDtoToAnswer(answerDto);
            Answer updatedAnswer = answerDao.save(answer);
            return answerMapper.answerToAnswerDto(updatedAnswer);
        }
    }

    @Override
    public AnswerDto delete(AnswerDto answerDto) {
        Answer answer = answerMapper.answerDtoToAnswer(answerDto);
        answer.setActive(false);
        AnswerDto deletedAnswerDto = update(answerMapper.answerToAnswerDto(answer));
        return deletedAnswerDto;
    }

    @Override
    public List<AnswerDto> findAllByQuestion(QuestionDto questionDto) {
        List<Answer> answers = answerDao.findAllByQuestionAndActiveTrue(questionDto.getId());
        return answerMapper.answerListToAnswerDtoList(answers);
    }
    @Override
    public List<AnswerDto> findAllByQuestionAndTrue(QuestionDto questionDto) {
        List<Answer> answers = answerDao.findAllByQuestionAnd_trueAndActive(questionDto.getId());
        return answerMapper.answerListToAnswerDtoList(answers);
    }



}
