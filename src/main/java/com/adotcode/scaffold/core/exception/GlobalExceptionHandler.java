package com.adotcode.scaffold.core.exception;

import com.adotcode.scaffold.core.enums.result.ResultCodeEnum;
import com.adotcode.scaffold.core.exception.application.BaseException;
import com.adotcode.scaffold.core.exception.application.ForbiddenException;
import com.adotcode.scaffold.core.exception.application.GenericException;
import com.adotcode.scaffold.core.exception.application.IllegalParameterException;
import com.adotcode.scaffold.core.exception.application.IllegalPropertiesException;
import com.adotcode.scaffold.core.exception.application.NullOrEmptyException;
import com.adotcode.scaffold.core.exception.application.ParseFailedException;
import com.adotcode.scaffold.core.exception.application.UnAuthorizedException;
import com.adotcode.scaffold.core.response.HttpResult;
import com.adotcode.scaffold.core.response.HttpResult.ErrorWrapper;
import com.adotcode.scaffold.core.util.i18n.I18nMessageUtils;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常统一处理器
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-21
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * 默认异常处理(未匹配到任何预知异常，服务器内部错误)
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public HttpResult onException(Exception e) {
    String message = I18nMessageUtils
        .translate(ResultCodeEnum.INTERNAL_SERVER_ERROR.reasonPhrase());
    log.error(message, e);
    return wrapperErrorResult(ResultCodeEnum.INTERNAL_SERVER_ERROR, e.getMessage());
  }

  /**
   * 用户未授权异常处理
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(UnAuthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public HttpResult onUnauthorizedException(UnAuthorizedException e) {
    return wrapperErrorResult(e);
  }

  /**
   * 无权访问异常处理
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(ForbiddenException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ResponseBody
  public HttpResult onForbiddenException(ForbiddenException e) {
    return wrapperErrorResult(e);
  }

  /**
   * null或空异常处理
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(NullOrEmptyException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public HttpResult onNullOrEmptyException(NullOrEmptyException e) {
    return wrapperErrorResult(e);
  }

  /**
   * 非法属性异常
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(IllegalPropertiesException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public HttpResult onIllegalPropertiesException(IllegalPropertiesException e) {
    return wrapperErrorResult(e);
  }

  /**
   * 非法参数异常
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(IllegalParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public HttpResult onIllegalParameterException(IllegalParameterException e) {
    return wrapperErrorResult(e);
  }

  /**
   * validation 异常处理
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public HttpResult onConstraintViolationException(ConstraintViolationException e) {
    Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
    if (!CollectionUtils.isEmpty(constraintViolations)) {
      List<String> errorMessage = constraintViolations
          .stream()
          .map(error -> I18nMessageUtils.translate(error.getMessage()))
          .collect(Collectors.toList());
      return wrapperErrorResult(ResultCodeEnum.CONSTRAINT_VIOLATION, errorMessage);
    }
    return wrapperErrorResult(e);
  }

  /**
   * validation 异常处理
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public HttpResult onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    List<FieldError> objectErrors = e.getBindingResult().getFieldErrors();
    if (!CollectionUtils.isEmpty(objectErrors)) {
      List<String> errorMessage = objectErrors
          .stream()
          .map(error -> String.format("[%s]%s", error.getField(),
              I18nMessageUtils.translate(error.getDefaultMessage(), error.getArguments())))
          .collect(Collectors.toList());
      return wrapperErrorResult(ResultCodeEnum.METHOD_ARGUMENT_NOT_VALID, errorMessage);
    }
    return wrapperErrorResult(e);
  }


  /**
   * Request Parameter 异常处理
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public HttpResult onMissingServletRequestParameterException(
      MissingServletRequestParameterException e) {
    return wrapperErrorResult(e);
  }

  /**
   * 通用异常
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(GenericException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public HttpResult onGenericException(GenericException e) {
    return wrapperErrorResult(e);
  }

  /**
   * 转失败异常
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(ParseFailedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public HttpResult onParseFailedException(ParseFailedException e) {
    return wrapperErrorResult(e);
  }

  /**
   * 错误信息返回辅助
   *
   * @param e Exception
   * @return HttpResult
   */
  private HttpResult wrapperErrorResult(Exception e) {
    if (e instanceof MissingServletRequestParameterException) {
      MissingServletRequestParameterException missingServletRequestParameterException =
          (MissingServletRequestParameterException) e;
      String message = I18nMessageUtils.translate("exception.parameter.required.not.present",
          missingServletRequestParameterException.getParameterName());
      return HttpResult.error(ErrorWrapper.newInstance(message));
    }
    return HttpResult
        .error(ErrorWrapper.newInstance(I18nMessageUtils.translate(e.getMessage())));
  }

  /**
   * 错误信息返回辅助
   *
   * @param code 错误代码
   * @param errorObj 错误消息描述
   * @return HttpResult
   */
  private HttpResult wrapperErrorResult(ResultCodeEnum code, Object errorObj) {
    return HttpResult.error(code.value(), I18nMessageUtils.translate(code.reasonPhrase()),
        ErrorWrapper.newInstance(errorObj));
  }

  /**
   * 错误信息返回辅助
   *
   * @param e BaseException
   * @return HttpResult
   */
  private HttpResult wrapperErrorResult(BaseException e) {
    return HttpResult.error(e.getCode(), I18nMessageUtils.translate(e.getMessage()),
        ErrorWrapper.newInstance(I18nMessageUtils.translate(e.getMessage())));
  }
}
