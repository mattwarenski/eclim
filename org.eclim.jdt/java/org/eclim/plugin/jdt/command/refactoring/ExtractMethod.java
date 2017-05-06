package org.eclim.plugin.jdt.command.refactoring;

import org.eclim.command.CommandLine;
import org.eclim.annotation.Command;

import org.eclim.plugin.core.command.refactoring.AbstractRefactorCommand;
import org.eclim.plugin.core.command.refactoring.Refactor;

import org.eclim.plugin.jdt.util.JavaUtils;

import org.eclim.command.Options;

import org.eclipse.jdt.core.ICompilationUnit;

import org.eclipse.jdt.internal.corext.refactoring.code.ExtractMethodRefactoring;

@Command(
  name = "java_refactor_extract_method",
  options = 
  "REQUIRED p project ARG," + 
  "REQUIRED f file ARG," +
  "REQUIRED n name ARG," +
  "REQUIRED e encoding ARG," +
  "REQUIRED o offset ARG," +
  "REQUIRED l length ARG," +
  "OPTIONAL v preview NOARG"
)
public class ExtractMethod extends AbstractRefactorCommand
{
  @Override
  public Refactor createRefactoring(CommandLine commandLine)
    throws Exception 
  {
    String project = commandLine.getValue(Options.PROJECT_OPTION);
    String file = commandLine.getValue(Options.FILE_OPTION);
    String name = commandLine.getValue(Options.NAME_OPTION);

    int offset = getOffset(commandLine);
    int length = commandLine.getIntValue(Options.LENGTH_OPTION);

    ICompilationUnit src = JavaUtils.getCompilationUnit(project, file);

    ExtractMethodRefactoring refactoring = new ExtractMethodRefactoring(src, offset, length);
    refactoring.setMethodName(name);

    return new Refactor(refactoring);
  }
}
