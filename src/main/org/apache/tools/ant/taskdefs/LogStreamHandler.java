/*
 * Copyright  2000,2002,2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.apache.tools.ant.taskdefs;

import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * Logs standard output and error of a subprocess to the log system of ant.
 *
 * @author thomas.haas@softwired-inc.com
 * @since Ant 1.2
 */
public class LogStreamHandler extends PumpStreamHandler {

    /**
     * Creates log stream handler
     *
     * @param task the task for whom to log
     * @param outlevel the loglevel used to log standard output
     * @param errlevel the loglevel used to log standard error
     */
    public LogStreamHandler(Task task, int outlevel, int errlevel) {
        super(new LogOutputStream(task, outlevel),
              new LogOutputStream(task, errlevel));
    }

    /**
     * Stop the log stream handler.
     */
    public void stop() {
        super.stop();
        try {
            getErr().close();
            getOut().close();
        } catch (IOException e) {
            // plain impossible
            throw new BuildException(e);
        }
    }
}
